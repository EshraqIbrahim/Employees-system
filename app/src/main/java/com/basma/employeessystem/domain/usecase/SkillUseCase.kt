package com.basma.employeessystem.domain.usecase

import com.basma.employeessystem.domain.model.Skill
import com.basma.employeessystem.domain.repository.SkillRepository
import javax.inject.Inject

class SkillUseCase @Inject constructor(private val skillRepository: SkillRepository) {
    suspend fun getSkills() = skillRepository.getSkills()

    suspend fun saveSkills(skills: List<Skill>) = skillRepository.saveSkills(skills)
}