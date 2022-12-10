package com.basma.employeessystem.presentation.employeeForm.view

import androidx.activity.viewModels
import com.basma.employeessystem.common.UploadFileViewModel
import com.basma.employeessystem.presentation.employeeForm.viewModel.AddEmployeeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddEmployeeActivity : FormEmployeeActivity() {
    override val viewModel by viewModels<AddEmployeeViewModel>()
    override val uploadFileViewModel: UploadFileViewModel
        get() = viewModel
}