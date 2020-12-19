package just.cleandemo.network

import io.reactivex.Observable
import just.cleandemo.model.postResponse.PostAPIResponse
import just.cleandemo.model.postcommentResponse.PostCommentResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface APIService {

    @GET("posts")
    fun getPosts(): Observable<PostAPIResponse>

    @GET("posts/{post_Id}/comments")
    fun getPostsComments(@Path("post_Id") post_Id: Int): Observable<PostCommentResponse>


    companion object {
        const val Base_Url = "http://jsonplaceholder.typicode.com/"
    }
}
