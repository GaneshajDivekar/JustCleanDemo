package just.cleandemo.model.databaseclass

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts_db")
class PostsDB {

    @PrimaryKey(autoGenerate = true)
    var postsId: Long = 0L

    @ColumnInfo(name = "userId")
    var userId: String? = null

    @ColumnInfo(name = "id")
    var id: String? = null

    @ColumnInfo(name = "title")
    var title: String? = null

    @ColumnInfo(name = "body")
    var body: String? = null


}