package com.basma.employeessystem.presentation.employeeForm.viewModel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basma.employeessystem.common.SingleLiveEvent
import com.basma.employeessystem.common.UploadFileViewModel
import com.basma.employeessystem.domain.model.Employee
import com.basma.employeessystem.domain.model.Skill
import com.basma.employeessystem.domain.usecase.SkillUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class FormEmployeeViewModel constructor(private val skillUseCase: SkillUseCase) : ViewModel(), UploadFileViewModel {
    var employee = Employee(name = "")
    val validateInputLiveData = MutableLiveData<Boolean>()
    val onSaveFinished = SingleLiveEvent<Void>()
    override var fileUri: Uri? = null
    override val notifyFileInserted = SingleLiveEvent<Void>()
    val notifyChosenSkillsListChanged = SingleLiveEvent<Void>()
    val notifySkillsListChanged = SingleLiveEvent<Void>()
    val notifySkillRemoved = SingleLiveEvent<Int>()
    val notifySkillAdded = SingleLiveEvent<Int>()
    var chosenSkillsList = ArrayList<Skill>()
    var skillsList = ArrayList<Skill>()

    init {
        validateInput()
        loadSkillsList()
    }

    fun setEmployeePhotoPath() {
        employee.photoPath = fileUri.toString()
    }

    private fun loadSkillsList() {
        viewModelScope.launch(Dispatchers.IO) {
            skillsList = ArrayList(skillUseCase.getSkills())
            withContext(Dispatchers.Main) { notifySkillsListChanged.call() }
        }
    }

    fun onSkillChosen(skill: Skill) {
        if (chosenSkillsList.firstOrNull { it.id == skill.id } != null) return
        chosenSkillsList.add(skill)
        if (chosenSkillsList.size == 1) notifyChosenSkillsListChanged.call() else notifySkillAdded.value = chosenSkillsList.size - 1
    }

    val onSkillRemoved: (Int) -> Unit = { id ->
        val index = chosenSkillsList.indexOfFirst { it.id == id }
        chosenSkillsList.removeAt(index)
        notifySkillRemoved.value = index
    }

    fun validateInput() {
        validateInputLiveData.value = employee.name.isNotEmpty()
    }

    fun onNameChanged(name: String) {
        employee.name = name
        validateInput()
    }

    fun onEmailChanged(email: String) {
        employee.email = email
        validateInput()
    }

    abstract fun onEmployeeSaved()
}