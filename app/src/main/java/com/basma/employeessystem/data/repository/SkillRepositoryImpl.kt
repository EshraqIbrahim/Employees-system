package com.basma.employeessystem.data.repository

import com.basma.employeessystem.data.db.dao.SkillDao
import com.basma.employeessystem.data.db.model.SkillEntity
import com.basma.employeessystem.domain.model.Skill
import com.basma.employeessystem.domain.repository.SkillRepository
import javax.inject.Inject

class SkillRepositoryImpl @Inject constructor(private val skillDao: SkillDao) : SkillRepository {

    override suspend fun saveSkills(skills: List<Skill>) {
        skillDao.deleteAndSaveSkills(skills.map { SkillEntity.fromDomain(it) })
    }

    override suspend fun getSkills(): List<Skill> {
        return skillDao.getSkills().map { it.toDomain() }
    }
}