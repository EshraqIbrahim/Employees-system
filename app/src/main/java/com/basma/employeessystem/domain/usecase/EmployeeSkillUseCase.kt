package com.basma.employeessystem.domain.usecase

import com.basma.employeessystem.domain.model.EmployeeSkill
import com.basma.employeessystem.domain.model.Skill
import com.basma.employeessystem.domain.repository.EmployeeSkillRepository
import javax.inject.Inject

class EmployeeSkillUseCase @Inject constructor(private val employeeSkillRepository: EmployeeSkillRepository) {
    suspend fun getEmployeeSkills(employeeId: Int) = employeeSkillRepository.getEmployeeSkills(employeeId)

    suspend fun editEmployeeSkills(employeeId: Int, skills: List<Skill>) =
        employeeSkillRepository.editEmployeeSkills(employeeId, skills.map { EmployeeSkill(employeeId, it.id!!) })

    suspend fun saveEmployeeSkills(employeeId: Int, skills: List<Skill>) =
        employeeSkillRepository.saveEmployeeSkills(skills.map { EmployeeSkill(employeeId, it.id!!) })
}