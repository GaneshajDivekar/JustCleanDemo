package just.cleandemo.model.postcommentResponse


import com.google.gson.annotations.SerializedName

data class PostCommentResponseItem(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)