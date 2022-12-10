package com.basma.employeessystem.presentation.employeeForm.view

import android.os.Bundle
import androidx.activity.viewModels
import com.basma.employeessystem.common.UploadFileViewModel
import com.basma.employeessystem.domain.model.Employee
import com.basma.employeessystem.presentation.employeeForm.viewModel.EditEmployeeViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EditEmployeeActivity : FormEmployeeActivity() {
    override val viewModel by viewModels<EditEmployeeViewModel>()
    override val uploadFileViewModel: UploadFileViewModel
        get() = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setEmployeeData()
    }

    private fun setEmployeeData() {
        intent.extras?.getParcelable<Employee>("Employee")?.let {
            viewModel.employee = it
            viewModel.loadEmployeeSkills()
            binding.nameEditText.setText(it.name)
            it.email?.let { email -> binding.emailEditText.setText(email) }
            it.photoPath?.let { photoPath ->
                Glide.with(this)
                    .load(photoPath)
                    .centerCrop()
                    .into(binding.employeePhoto)
            }
        }
    }
}