package com.basma.employeessystem.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.basma.employeessystem.R
import com.basma.employeessystem.databinding.ItemEmployeeBinding
import com.basma.employeessystem.domain.model.Employee
import com.bumptech.glide.Glide


class EmployeeAdapter(
    private val employees: ArrayList<Employee>,
    val onEditButtonClicked: (Int) -> Unit,
    val onDeleteButtonClicked: (Int) -> Unit,
) : RecyclerView.Adapter<EmployeeAdapter.EmployeesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EmployeesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_employee, parent, false))

    override fun onBindViewHolder(holder: EmployeesViewHolder, position: Int) = holder.bind(employees[position])

    override fun getItemCount() = employees.size

    inner class EmployeesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemEmployeeBinding.bind(itemView)

        fun bind(employee: Employee) {
            binding.employeeName.text = employee.name
            binding.emailTextView.text = employee.email
            binding.editButton.setOnClickListener { onEditButtonClicked(employee.id!!) }
            binding.deleteButton.setOnClickListener { onDeleteButtonClicked(employee.id!!) }
            Glide.with(itemView.context)
                .load(employee.photoPath)
                .centerCrop()
                .into(binding.employeeImage)
        }
    }
}