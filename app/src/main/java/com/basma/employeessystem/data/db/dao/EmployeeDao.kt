package com.basma.employeessystem.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.basma.employeessystem.data.db.model.EmployeeEntity

@Dao
interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveEmployee(employeeEntity: EmployeeEntity) : Long

    @Query("UPDATE EmployeeEntity SET name = :name, photoPath = :photoPath, email = :email WHERE id = :id")
    suspend fun updateEmployee(
        id: Int,
        name: String,
        photoPath: String?,
        email: String?
    )

    @Query("DELETE FROM EmployeeEntity WHERE id = :id")
    suspend fun deleteEmployee(id: String)

    @Query("SELECT * FROM EmployeeEntity")
    suspend fun getEmployees() : List<EmployeeEntity>

    @Query("SELECT * FROM EmployeeEntity WHERE id = :id")
    suspend fun getEmployee(id: Int) : EmployeeEntity?
}
