package com.example.cftlab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cftlab.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking


class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val valuteDao = (application as DatabaseApplication).database.valuteDao()

        runBlocking(Dispatchers.IO) {
            if (valuteDao.getAll().isEmpty()) {
                viewModel.getValutes()
            }
        }

        binding.refresh.setOnRefreshListener {
            viewModel.getValutes()
        }

        observeModel()
    }

    private fun observeModel() {
        viewModel.getState().observe(this) {
            when (it) {
                is State.Pending -> {
                    binding.refresh.isRefreshing = true
                }
                is State.Fail -> {
                    binding.refresh.isRefreshing = false
                }
                is State.Success -> {
                    binding.valuteRecycler.adapter = ValuteRecyclerAdapter(viewModel.valuteList)
                    binding.valuteRecycler.layoutManager = LinearLayoutManager(this)
                    binding.refresh.isRefreshing = false
                }
            }
        }
    }
}