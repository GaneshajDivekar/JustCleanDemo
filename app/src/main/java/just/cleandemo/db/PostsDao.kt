package just.cleandemo.db

import androidx.room.Dao
import androidx.room.Insert
import just.cleandemo.model.databaseclass.PostsDB

@Dao
interface PostsDao {

    @Insert
    fun insertData(postsDB: PostsDB)

}