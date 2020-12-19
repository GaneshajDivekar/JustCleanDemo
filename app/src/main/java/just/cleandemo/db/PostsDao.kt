package just.cleandemo.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import just.cleandemo.model.databaseclass.PostsDB

@Dao
interface PostsDao {

    @Insert
    fun insertData(postsDB: PostsDB)

    @Query("SELECT * FROM posts_db")
    fun getPosts(): LiveData<List<PostsDB>>

    @Query("SELECT * FROM posts_db")
    fun getPostss(): LiveData<List<PostsDB>>

    @Query("SELECT * FROM posts_db Where favoriteFlag=:favourite_id")
    fun getFavoritePosts(favourite_id: String): LiveData<List<PostsDB>>

    @Query("Update posts_db   SET  favoriteFlag =:favFlag  WHERE  id =:ID")
    fun updateStatusPos(ID: String, favFlag: String)

    @Query("DELETE FROM posts_db")
    fun deletePost()
}