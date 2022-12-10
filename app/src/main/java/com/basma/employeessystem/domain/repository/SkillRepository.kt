package com.basma.employeessystem.domain.repository

import com.basma.employeessystem.domain.model.Skill

interface SkillRepository {
    suspend fun saveSkills(skills: List<Skill>)
    suspend fun getSkills(): List<Skill>
}