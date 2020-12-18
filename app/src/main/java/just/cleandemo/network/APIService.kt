package just.cleandemo.network

import io.reactivex.Observable
import just.cleandemo.model.postResponse.PostAPIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface APIService {

    @GET("posts")
    fun getPosts(): Observable<PostAPIResponse>

    companion object {
        const val Base_Url = "http://jsonplaceholder.typicode.com/"
    }
}
