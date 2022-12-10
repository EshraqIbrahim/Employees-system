package com.basma.employeessystem.domain.repository

import com.basma.employeessystem.domain.model.EmployeeSkill
import com.basma.employeessystem.domain.model.Skill

interface EmployeeSkillRepository {
    suspend fun saveEmployeeSkills(employeeSkills: List<EmployeeSkill>)
    suspend fun editEmployeeSkills(employeeId: Int, employeeSkills: List<EmployeeSkill>)
    suspend fun getEmployeeSkills(employeeId: Int): List<Skill>
}