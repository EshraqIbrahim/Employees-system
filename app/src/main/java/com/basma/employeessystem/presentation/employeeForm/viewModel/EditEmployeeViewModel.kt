package com.basma.employeessystem.presentation.employeeForm.viewModel

import androidx.lifecycle.viewModelScope
import com.basma.employeessystem.domain.usecase.EmployeeSkillUseCase
import com.basma.employeessystem.domain.usecase.EmployeeUseCase
import com.basma.employeessystem.domain.usecase.SkillUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class EditEmployeeViewModel @Inject constructor(private val employeeUseCase: EmployeeUseCase,
                                               private val skillUseCase: SkillUseCase,
                                               private val employeeSkillUseCase: EmployeeSkillUseCase
) : FormEmployeeViewModel(skillUseCase) {

    override fun onEmployeeSaved() {
        viewModelScope.launch(Dispatchers.IO) {
            employeeUseCase.updateEmployee(employee)
            employeeSkillUseCase.editEmployeeSkills(employee.id!!, chosenSkillsList)
            withContext(Dispatchers.Main) { onSaveFinished.call() }
        }
    }

    fun loadEmployeeSkills() {
        viewModelScope.launch(Dispatchers.IO) {
            chosenSkillsList = ArrayList(employeeSkillUseCase.getEmployeeSkills(employee.id!!))
            withContext(Dispatchers.Main) { notifyChosenSkillsListChanged.call() }
        }
    }
}