package ca.qc.cgodin.laboratoire10

import android.content.Context
import androidx.room.Database

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kotlinx.coroutines.CoroutineScope
import models.Article
import models.ArticleDao

@Database(entities = arrayOf(Article::class), version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ArticleRoomDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao

    companion object {
        //Singleton to prevent multiple instances of database openint at the same time
        @Volatile
        private var INSTANCE: ArticleRoomDatabase? = null

        fun getDatabase(context: Context): ArticleRoomDatabase {
            var tempInstance: ArticleRoomDatabase? = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                ArticleRoomDatabase::class.java,
                "article_database"
            ).build()
            return INSTANCE as ArticleRoomDatabase

        }

    }

}