package com.example.cftlab

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.cftlab.localBd.Valute
import com.example.cftlab.model.ValuteInfo
import com.example.cftlab.model.Valutes
import com.example.cftlab.retrofit.CbrApi
import com.example.cftlab.retrofit.CbrClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val provider: CbrApi = CbrClient.retrofit.create(CbrApi::class.java)
    private val valuteState = MutableLiveData<State<Valutes>>()
    var valuteList = mutableListOf<ValuteInfo>()
    private val valuteDao = (application as DatabaseApplication).database.valuteDao()

    init {
        runBlocking(Dispatchers.IO) {
            if (valuteDao.getAll().isNotEmpty()) {
                valuteList = valuteTovaluteInfo(valuteDao.getAll()).toMutableList()
                valuteState.postValue(State.Success())
            }
        }
    }

    fun getState() = valuteState

    fun getValutes() {
        valuteState.postValue(State.Pending())
        provider.getDailyCurse()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.i("tag", it.toString())
                    valuteList.clear()
                    valuteList.addAll(listOf(it.valutes.amd, it.valutes.aud, it.valutes.azn,
                        it.valutes.bgn, it.valutes.brl, it.valutes.byn, it.valutes.cad,
                        it.valutes.chf, it.valutes.cny, it.valutes.czk, it.valutes.dkk,
                        it.valutes.eur, it.valutes.gbp, it.valutes.hkd, it.valutes.huf,
                        it.valutes.inr, it.valutes.jpy, it.valutes.kgs, it.valutes.krw,
                        it.valutes.kzt, it.valutes.mdl, it.valutes.nok, it.valutes.pln,
                        it.valutes.ron, it.valutes.sek, it.valutes.sgd, it.valutes.tjs,
                        it.valutes.tmt, it.valutes.try_, it.valutes.uah, it.valutes.usd,
                        it.valutes.uzs, it.valutes.xdr, it.valutes.zar))

                    runBlocking(Dispatchers.IO) {
                        valuteDao.cleanAll()
                        valuteDao.insertAll(valuteInfoToValute(valuteList))
                    }

                    valuteState.postValue(State.Success())
                },
                {
                    Log.i("tag", it.toString())
                    valuteState.postValue(State.Fail(it))
                }
            )
    }

    private fun valuteInfoToValute(valuteInfo: List<ValuteInfo>): List<Valute> {
        val newList = mutableListOf<Valute>()
        valuteInfo.forEach {
            val valute =
                Valute(it.id, it.numCode, it.charCode, it.nominal, it.name, it.value, it.previous)
            newList.add(valute)
        }
        return newList.toList()
    }

    private fun valuteTovaluteInfo(valute: List<Valute>): List<ValuteInfo> {
        val newList = mutableListOf<ValuteInfo>()
        valute.forEach {
            val valute =
                ValuteInfo(it.id, it.numCode, it.charCode, it.nominal, it.name, it.value, it.previous)
            newList.add(valute)
        }
        return newList.toList()
    }
}