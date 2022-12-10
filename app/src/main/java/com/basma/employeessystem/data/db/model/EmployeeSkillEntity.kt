package com.basma.employeessystem.data.db.model

import androidx.room.Entity
import androidx.room.ForeignKey
import com.basma.employeessystem.domain.model.EmployeeSkill

@Entity(tableName = "EmployeeSkillEntity",
    primaryKeys = ["employeeId","skillId"])
class EmployeeSkillEntity(
    var employeeId: Int,
    var skillId : Int
) {
    companion object {
        fun fromDomain(employeeSkill: EmployeeSkill) = EmployeeSkillEntity(
            employeeId = employeeSkill.employeeId,
            skillId = employeeSkill.skillId
        )
    }

    fun toDomain() = EmployeeSkill(employeeId, skillId)
}