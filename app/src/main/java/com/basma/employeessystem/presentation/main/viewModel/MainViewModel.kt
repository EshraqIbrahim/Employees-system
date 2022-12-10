package com.basma.employeessystem.presentation.main.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basma.employeessystem.domain.model.Employee
import com.basma.employeessystem.domain.usecase.EmployeeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val employeeUseCase: EmployeeUseCase) : ViewModel() {
    val employeesLiveData = MutableLiveData<ArrayList<Employee>>()
    val navigateToEditScreen = MutableLiveData<Employee>()

    fun getEmployees() {
        viewModelScope.launch(Dispatchers.IO) {
            val employees = employeeUseCase.getEmployees()
            withContext(Dispatchers.Main) {
                employeesLiveData.value = ArrayList(employees)
            }
        }
    }

    val onEditButtonClicked: (Int) -> Unit = { employeeId ->
        employeesLiveData.value?.firstOrNull { it.id == employeeId }?.let {
            navigateToEditScreen.value = it
        }
    }

    val onDeleteButtonClicked: (Int) -> Unit = { employeeId ->

    }
}