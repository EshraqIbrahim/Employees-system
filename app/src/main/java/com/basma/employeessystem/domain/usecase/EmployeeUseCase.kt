package com.basma.employeessystem.domain.usecase

import com.basma.employeessystem.domain.model.Employee
import com.basma.employeessystem.domain.repository.EmployeeRepository
import javax.inject.Inject

class EmployeeUseCase @Inject constructor(private val employeeRepository: EmployeeRepository) {

    suspend fun getEmployee(id: Int) = employeeRepository.getEmployee(id)

    suspend fun updateEmployee(employee: Employee) = employeeRepository.updateEmployee(employee)

    suspend fun getEmployees() = employeeRepository.getEmployees()

    suspend fun saveEmployee(employee: Employee) : Int =
        employeeRepository.saveEmployee(employee)
}