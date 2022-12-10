package com.basma.employeessystem.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.basma.employeessystem.data.db.dao.EmployeeDao
import com.basma.employeessystem.data.db.model.EmployeeEntity
import com.basma.employeessystem.domain.model.Employee
import com.basma.employeessystem.domain.repository.EmployeeRepository
import javax.inject.Inject

class EmployeeRepositoryImpl @Inject constructor(private val employeeDao: EmployeeDao) : EmployeeRepository {
    override suspend fun saveEmployee(employee: Employee): Int {
        return employeeDao.saveEmployee(EmployeeEntity.fromDomain(employee)).toInt()
    }

    override suspend fun updateEmployee(employee: Employee) {
        employeeDao.updateEmployee(
            employee.id!!,
            employee.name,
            employee.photoPath,
            employee.email
        )
    }

    override suspend fun getEmployees(): List<Employee> {
        return employeeDao.getEmployees().map { it.toDomain() }
    }

    override suspend fun getEmployee(id: Int): Employee? {
        return employeeDao.getEmployee(id)?.toDomain()
    }
}