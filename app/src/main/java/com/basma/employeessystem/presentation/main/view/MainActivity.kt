package com.basma.employeessystem.presentation.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.basma.employeessystem.databinding.ActivityMainBinding
import com.basma.employeessystem.presentation.main.EmployeeAdapter
import com.basma.employeessystem.presentation.main.viewModel.MainViewModel
import com.basma.employeessystem.presentation.employeeForm.view.AddEmployeeActivity
import com.basma.employeessystem.presentation.employeeForm.view.EditEmployeeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initializeListeners()
        setupObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getEmployees()
    }

    private fun initializeListeners() {
        binding.addEmployeeButton.setOnClickListener {
            startActivity(Intent(this, AddEmployeeActivity::class.java))
        }
    }

    private fun setupObservers() {
        viewModel.employeesLiveData.observe(this, {
            binding.employeesList.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = EmployeeAdapter(it, viewModel.onEditButtonClicked, viewModel.onDeleteButtonClicked)
            }
        })
        viewModel.navigateToEditScreen.observe(this, {
            val intent = Intent(this, EditEmployeeActivity::class.java)
            intent.putExtra("Employee", it)
            startActivity(intent)
        })
    }
}