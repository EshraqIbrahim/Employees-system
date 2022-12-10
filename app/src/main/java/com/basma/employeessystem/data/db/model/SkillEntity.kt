package com.basma.employeessystem.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.basma.employeessystem.domain.model.Employee
import com.basma.employeessystem.domain.model.Skill


@Entity(tableName = "SkillEntity")
class SkillEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var name: String = ""
) {
    companion object {
        fun fromDomain(skill: Skill) = SkillEntity(
            name = skill.name
        )
    }

    fun toDomain() = Skill(id, name)
}
