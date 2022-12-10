package com.basma.employeessystem

import android.app.Application
import com.basma.employeessystem.domain.model.Skill
import com.basma.employeessystem.domain.usecase.SkillUseCase
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class EmployeesApp : Application() {
    @Inject
    lateinit var skillUseCase: SkillUseCase
    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()
        applicationScope.launch(Dispatchers.IO) {
            val isSkillsEmpty = skillUseCase.getSkills().isEmpty()
            if (isSkillsEmpty) {
                skillUseCase.saveSkills(
                    listOf(
                        Skill(name = "Android"),
                        Skill(name = "Net"),
                        Skill(name = "PHP"),
                        Skill(name = "MATLAB"),
                        Skill(name = "Kotlin"),
                        Skill(name = "Ruby on rails"),
                        Skill(name = "Java"),
                        Skill(name = "C"),
                        Skill(name = "C++"),
                    )
                )
            }
        }
    }
}