package just.cleandemo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import just.cleandemo.model.databaseclass.PostsDB


@Database(entities = [PostsDB::class], version = 2, exportSchema = false)
abstract class JustCleanDB : RoomDatabase() {
    abstract fun postDao(): PostsDao?
}