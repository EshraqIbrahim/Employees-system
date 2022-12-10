package com.basma.employeessystem.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.basma.employeessystem.data.db.dao.EmployeeDao
import com.basma.employeessystem.data.db.dao.EmployeeSkillDao
import com.basma.employeessystem.data.db.dao.SkillDao
import com.basma.employeessystem.data.db.model.EmployeeEntity
import com.basma.employeessystem.data.db.model.EmployeeSkillEntity
import com.basma.employeessystem.data.db.model.SkillEntity

@Database(
    entities = [
        EmployeeEntity::class,
        SkillEntity::class,
        EmployeeSkillEntity::class
    ],
    version = 1, exportSchema = false,
)

abstract class EmployeesAppDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao
    abstract fun skillDao(): SkillDao
    abstract fun employeeSkillDao(): EmployeeSkillDao

    companion object {
        @Volatile private var instance: EmployeesAppDatabase? = null

        fun getDatabase(context: Context): EmployeesAppDatabase = instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) = Room.databaseBuilder(appContext, EmployeesAppDatabase::class.java,  "employees_db")
            .fallbackToDestructiveMigration()
            .build()
    }
}