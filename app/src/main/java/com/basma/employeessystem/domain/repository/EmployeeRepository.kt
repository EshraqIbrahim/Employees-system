package com.basma.employeessystem.domain.repository

import androidx.lifecycle.LiveData
import com.basma.employeessystem.domain.model.Employee


interface EmployeeRepository {
    suspend fun saveEmployee(employee: Employee) : Int
    suspend fun updateEmployee(employee: Employee)
    suspend fun getEmployees(): List<Employee>
    suspend fun getEmployee(id: Int): Employee?
}