package com.basma.employeessystem.di

import android.content.Context
import androidx.room.Room
import com.basma.employeessystem.data.db.EmployeesAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): EmployeesAppDatabase {
        return EmployeesAppDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideEmployeeDao(employeesAppDatabase: EmployeesAppDatabase) = employeesAppDatabase.employeeDao()

    @Provides
    @Singleton
    fun provideSkillDao(employeesAppDatabase: EmployeesAppDatabase) = employeesAppDatabase.skillDao()

    @Provides
    @Singleton
    fun provideEmployeeSkillDao(employeesAppDatabase: EmployeesAppDatabase) = employeesAppDatabase.employeeSkillDao()
}