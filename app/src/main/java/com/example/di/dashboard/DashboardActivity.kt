package com.example.di.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.di.R
import com.example.di.databinding.ActivityDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    private lateinit var dashboardBinding: ActivityDashboardBinding
    private val dashBoardViewModel : DashBoardViewModel by viewModels()
    private lateinit var dashBoardAdapter: DashBoardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashboardBinding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(dashboardBinding.root)
        initList()
        initObserve()
        dashBoardViewModel.loadUser()
    }

    private fun initObserve() {
        dashBoardViewModel.loadUser.observe(this) {result ->
            when (result) {
                is DashBoardViewModel.LoadUser.ResultOk -> {
                    dashBoardAdapter.addNewData(result.list)
                }
                is DashBoardViewModel.LoadUser.ResultError -> {
                    Toast.makeText(this, result.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initList() {
        dashboardBinding.recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            dashBoardAdapter = DashBoardAdapter(arrayListOf())
            adapter = dashBoardAdapter
        }
    }
}