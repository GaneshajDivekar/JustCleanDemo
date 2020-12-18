package just.cleandemo.di.module

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import just.cleandemo.db.JustCleanDB
import just.cleandemo.db.PostsDao
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class JustCleanDataBaseModule {

    @Provides
    @Singleton
    fun provideJustCleanDB(application: Application?): JustCleanDB {
        return Room.databaseBuilder(application!!, JustCleanDB::class.java, "Favorite Database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun providePostsDao(justCleanDB: JustCleanDB): PostsDao {
        return justCleanDB.postDao()!!
    }
}