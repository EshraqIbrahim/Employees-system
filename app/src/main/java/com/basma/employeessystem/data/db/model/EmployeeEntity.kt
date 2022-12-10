package com.basma.employeessystem.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.basma.employeessystem.domain.model.Employee

@Entity(tableName = "EmployeeEntity")
class EmployeeEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var name: String = "",
    var email: String? = null,
    var photoPath: String? = null
) {
    companion object {
        fun fromDomain(employee: Employee) = EmployeeEntity(
            name = employee.name,
            email = employee.email,
            photoPath = employee.photoPath
        )
    }

    fun toDomain() = Employee(id, name, email, photoPath)
}
