package com.example.cftlab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cftlab.databinding.ValuteItemBinding
import com.example.cftlab.model.ValuteInfo
import java.math.BigDecimal
import java.math.RoundingMode

class ValuteRecyclerAdapter(
    private val values: List<ValuteInfo>
) : RecyclerView.Adapter<ValuteRecyclerAdapter.ValuteItemViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ValuteItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.valute_item, parent, false)
        return ValuteItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ValuteItemViewHolder, position: Int) {
        holder.bind(values[position])
    }

    override fun getItemCount() = values.size


    inner class ValuteItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ValuteItemBinding.bind(view)

        fun bind(valute: ValuteInfo) = with(binding) {
            name.text = valute.name
            charCode.text = valute.charCode
            nominal.setText(valute.nominal.toString(), TextView.BufferType.EDITABLE)
            valueInRub.setText(valute.value.toString(), TextView.BufferType.EDITABLE)
            valueInRub.setOnEditorActionListener { _, i, _ ->
                if (i == EditorInfo.IME_ACTION_DONE && valueInRub.text.isNotEmpty()) {
                    val newNominal = (BigDecimal(valueInRub.text.toString().toDouble()/valute.value*valute.nominal)
                        .setScale(4, RoundingMode.HALF_EVEN)).toString()
                    nominal.setText(newNominal, TextView.BufferType.EDITABLE)
                    return@setOnEditorActionListener true
                }
                false
            }
            nominal.setOnEditorActionListener { _, i, _ ->
                if (i == EditorInfo.IME_ACTION_DONE && nominal.text.isNotEmpty()) {
                    val newRub = (BigDecimal((valute.value/valute.nominal)*nominal.text.toString().toDouble())
                        .setScale(4, RoundingMode.HALF_EVEN)).toString()
                    valueInRub.setText(newRub, TextView.BufferType.EDITABLE)
                    return@setOnEditorActionListener true
                }
                false
            }
        }
    }
}