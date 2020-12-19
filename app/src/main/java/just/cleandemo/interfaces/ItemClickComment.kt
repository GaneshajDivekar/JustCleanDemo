package just.cleandemo.interfaces

import just.cleandemo.model.databaseclass.PostsDB
import just.cleandemo.model.postcommentResponse.PostCommentResponse

interface ItemClickComment {
    fun onClick(pos: PostCommentResponse)
}