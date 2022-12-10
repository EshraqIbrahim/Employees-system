package com.basma.employeessystem.data.repository

import com.basma.employeessystem.data.db.dao.EmployeeSkillDao
import com.basma.employeessystem.data.db.model.EmployeeSkillEntity
import com.basma.employeessystem.domain.model.EmployeeSkill
import com.basma.employeessystem.domain.model.Skill
import com.basma.employeessystem.domain.repository.EmployeeSkillRepository
import javax.inject.Inject


class EmployeeSkillRepositoryImpl @Inject constructor(private val employeeSkillDao: EmployeeSkillDao) : EmployeeSkillRepository {
    override suspend fun saveEmployeeSkills(employeeSkills: List<EmployeeSkill>) {
        employeeSkillDao.saveEmployeeSkills(employeeSkills.map { EmployeeSkillEntity.fromDomain(it) } )
    }

    override suspend fun editEmployeeSkills(employeeId: Int, employeeSkills: List<EmployeeSkill>) {
        employeeSkillDao.deleteAndSaveEmployeeSkills(employeeId, employeeSkills.map { EmployeeSkillEntity.fromDomain(it) } )
    }

    override suspend fun getEmployeeSkills(employeeId: Int): List<Skill> {
        return employeeSkillDao.getEmployeeSkills(employeeId).map { it.toDomain() }
    }
}