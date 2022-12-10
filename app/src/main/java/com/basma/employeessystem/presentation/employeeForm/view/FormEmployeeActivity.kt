package com.basma.employeessystem.presentation.employeeForm.view

import android.Manifest
import android.R
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.widget.doAfterTextChanged
import com.basma.employeessystem.common.UploadFileView
import com.basma.employeessystem.databinding.ActivityEmployeeFormBinding
import com.basma.employeessystem.domain.model.Skill
import com.basma.employeessystem.presentation.employeeForm.viewModel.FormEmployeeViewModel
import com.basma.employeessystem.presentation.employeeForm.SkillAdapter
import com.basma.employeessystem.presentation.employeeForm.SkillDropDownAdapter
import com.bumptech.glide.Glide
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager


abstract class FormEmployeeActivity : AppCompatActivity(), UploadFileView {
    val binding by lazy { ActivityEmployeeFormBinding.inflate(layoutInflater) }
    abstract val viewModel : FormEmployeeViewModel

    private lateinit var skillsAdapter : SkillAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initializeListeners()
        setupObservers()
    }

    private fun initializeListeners() {
        binding.emailEditText.doAfterTextChanged { viewModel.onEmailChanged(it.toString()) }
        binding.nameEditText.doAfterTextChanged { viewModel.onNameChanged(it.toString()) }
        binding.saveButton.setOnClickListener { viewModel.onEmployeeSaved() }
        binding.addImageButton.setOnClickListener { checkStoragePermission(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
            )) }
        binding.skillsDropdown.setOnItemClickListener { p0, _, p2, _ ->
            val skill = p0?.adapter?.getItem(p2) as Skill?
            skill?.let {
                binding.skillsDropdown.setText("")
                viewModel.onSkillChosen(it)
                (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager?)
                    ?.hideSoftInputFromWindow(binding.skillsDropdown.windowToken, 0)
                binding.skillsDropdown.clearFocus()
            }
        }
    }

    private fun hasWriteStoragePermission(permissions: Array<String>) =
        Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                permissions.size == permissions.filter { checkSelfPermission(it) == PackageManager.PERMISSION_GRANTED }.size

    private fun checkStoragePermission(permissions: Array<String>) =
        if (hasWriteStoragePermission(permissions)) {
            performPickFile()
        } else {
            ActivityCompat.requestPermissions(this, permissions, READ_REQUEST_CODE)
        }

    private fun performPickFile() {
        initializeFilePicker(viewModel.createURI(this))
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == UploadFileView.READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.data?.let {
                readPickedFile(data)
            }
            viewModel.onFileSelected()
        }
    }

    private fun setupObservers() {
        viewModel.validateInputLiveData.observe(this, { binding.saveButton.isEnabled = it })
        viewModel.onSaveFinished.observe(this, {
            onBackPressed()
        })
        viewModel.notifyFileInserted.observe(this, {
            viewModel.setEmployeePhotoPath()
            Glide.with(this)
                .load(viewModel.fileUri)
                .centerCrop()
                .into(binding.employeePhoto)
        })
        viewModel.notifyChosenSkillsListChanged.observe(this, {
            binding.skillsRecyclerView.apply {
                val flexibleLayoutManager = FlexboxLayoutManager(this@FormEmployeeActivity)
                flexibleLayoutManager.flexDirection = FlexDirection.ROW
                layoutManager = flexibleLayoutManager
                skillsAdapter = SkillAdapter(viewModel.chosenSkillsList, viewModel.onSkillRemoved)
                adapter = skillsAdapter
            }
        })
        viewModel.notifySkillRemoved.observe(this, { skillsAdapter.notifyItemRemoved(it) })
        viewModel.notifySkillAdded.observe(this, { skillsAdapter.notifyItemInserted(it) })
        viewModel.notifySkillsListChanged.observe(this, {
            val skillDropDownAdapter = SkillDropDownAdapter(this, R.layout.simple_list_item_1, viewModel.skillsList)
            binding.skillsDropdown.setAdapter(skillDropDownAdapter)
        })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (permissionToWriteGranted(grantResults) && requestCode == READ_REQUEST_CODE) performPickFile()
    }

    private fun permissionToWriteGranted(grantResults: IntArray) =
        grantResults.isNotEmpty() && grantResults.size == grantResults.filter { it == PackageManager.PERMISSION_GRANTED }.size

    companion object {
        private const val READ_REQUEST_CODE = 2
    }
}