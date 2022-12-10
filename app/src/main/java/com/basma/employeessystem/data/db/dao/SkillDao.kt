package com.basma.employeessystem.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.basma.employeessystem.data.db.model.EmployeeEntity
import com.basma.employeessystem.data.db.model.SkillEntity

@Dao
interface SkillDao {
    @Insert()
    suspend fun saveSkills(skillsEntities: List<SkillEntity>)

    @Query("SELECT * FROM SkillEntity")
    suspend fun getSkills() : List<SkillEntity>

    @Query("DELETE FROM SkillEntity")
    suspend fun deleteSkills()

    @Transaction
    suspend fun deleteAndSaveSkills(skillsEntities: List<SkillEntity>) {
        deleteSkills()
        saveSkills(skillsEntities)
    }
}
