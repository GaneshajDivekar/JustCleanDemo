package just.cleandemo.model.postResponse


import com.google.gson.annotations.SerializedName

data class PostAPIResponseItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)