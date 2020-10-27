package ca.qc.cgodin.roomstudent


import android.content.Context
import androidx.room.Database

import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope


@Database(entities = arrayOf(Student::class), version = 1, exportSchema = false)
abstract class StudentRoomDatabase: RoomDatabase() {
    abstract fun studentDao(): StudentDao
    companion object {
        //Singleton to prevent multiple instances of database openint at the same time
        @Volatile
        private var INSTANCE: StudentRoomDatabase? = null

        fun getDatabase( context: Context,  viewModelScope: CoroutineScope): StudentRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                StudentRoomDatabase::class.java,
                "student_database"
            ).build()
            return INSTANCE as StudentRoomDatabase
        }
    }
}