package com.basma.employeessystem.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.basma.employeessystem.data.db.model.EmployeeSkillEntity
import com.basma.employeessystem.data.db.model.SkillEntity

@Dao
interface EmployeeSkillDao {
    @Insert()
    suspend fun saveEmployeeSkills(employeeSkillEntities: List<EmployeeSkillEntity>)

    @Query("DELETE FROM EmployeeSkillEntity WHERE employeeId = :employeeId")
    suspend fun deleteEmployeeSkills(employeeId: Int)

    @Query("SELECT SkillEntity.* FROM SkillEntity INNER JOIN EmployeeSkillEntity ON EmployeeSkillEntity.skillId = SkillEntity.id Where EmployeeSkillEntity.employeeId = :employeeId")
    suspend fun getEmployeeSkills(employeeId: Int) : List<SkillEntity>

    @Transaction
    suspend fun deleteAndSaveEmployeeSkills(employeeId: Int, employeeSkillEntities: List<EmployeeSkillEntity>) {
        deleteEmployeeSkills(employeeId)
        saveEmployeeSkills(employeeSkillEntities)
    }
}