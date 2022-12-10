package com.basma.employeessystem.di

import android.app.Application
import com.basma.employeessystem.data.db.dao.EmployeeDao
import com.basma.employeessystem.data.db.dao.EmployeeSkillDao
import com.basma.employeessystem.data.db.dao.SkillDao
import com.basma.employeessystem.data.repository.EmployeeRepositoryImpl
import com.basma.employeessystem.data.repository.EmployeeSkillRepositoryImpl
import com.basma.employeessystem.data.repository.SkillRepositoryImpl
import com.basma.employeessystem.domain.repository.EmployeeRepository
import com.basma.employeessystem.domain.repository.EmployeeSkillRepository
import com.basma.employeessystem.domain.repository.SkillRepository
import com.basma.employeessystem.domain.usecase.EmployeeSkillUseCase
import com.basma.employeessystem.domain.usecase.EmployeeUseCase
import com.basma.employeessystem.domain.usecase.SkillUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesEmployeeRepository(employeeDao: EmployeeDao): EmployeeRepository {
        return EmployeeRepositoryImpl(employeeDao)
    }

    @Provides
    fun providesSkillUseCase(skillRepository: SkillRepository): SkillUseCase {
        return SkillUseCase(skillRepository)
    }

    @Provides
    @Singleton
    fun providesSkillRepository(skillDao: SkillDao): SkillRepository {
        return SkillRepositoryImpl(skillDao)
    }


    @Provides
    fun providesEmployeeSkillUseCase(employeeSkillRepository: EmployeeSkillRepository): EmployeeSkillUseCase {
        return EmployeeSkillUseCase(employeeSkillRepository)
    }

    @Provides
    @Singleton
    fun providesEmployeeSkillRepository(employeeSkillDao: EmployeeSkillDao): EmployeeSkillRepository {
        return EmployeeSkillRepositoryImpl(employeeSkillDao)
    }

    @Provides
    fun providesEmployeeUseCase(employeeRepository: EmployeeRepository): EmployeeUseCase {
        return EmployeeUseCase(employeeRepository)
    }
}
