package just.cleandemo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import just.cleandemo.db.PostsDao
import just.cleandemo.model.databaseclass.PostsDB
import just.cleandemo.model.postResponse.PostAPIResponse
import just.cleandemo.model.postcommentResponse.PostCommentResponse
import just.cleandemo.network.APIService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiHelper: APIService,
    private val postsDao: PostsDao
) {

    var postAPIResponseLiveData = MutableLiveData<PostAPIResponse>()
    var postCommentResponseLiveData = MutableLiveData<PostCommentResponse>()

    fun putResponse(): MutableLiveData<PostAPIResponse> {
        apiHelper.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                { postResult ->
                    postAPIResponseLiveData.postValue(postResult)
                },
                { error ->
                    postAPIResponseLiveData.postValue(null)
                }
            )
        return postAPIResponseLiveData
    }


    fun getCommentResponse(ID: Int): MutableLiveData<PostCommentResponse> {
        apiHelper.getPostsComments(ID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                { postResult ->
                    postCommentResponseLiveData.postValue(postResult)
                },
                { error ->
                    postCommentResponseLiveData.postValue(null)
                }
            )
        return postCommentResponseLiveData
    }

    fun insertData(postsDB: PostsDB) {
        postsDao.insertData(postsDB)
    }

    fun getPostsData(): LiveData<List<PostsDB>> {
        return postsDao.getPosts()
    }

    fun getPostsDataa(): LiveData<List<PostsDB>> {
        return postsDao.getPostss()
    }

    fun getFavouriteData(favourite_id: String): LiveData<List<PostsDB>> {
        return postsDao.getFavoritePosts(favourite_id)
    }

    fun updateStatus(ID: String, favFlag: String) {
        postsDao.updateStatusPos(ID, favFlag)
    }

    fun DeleteData() {
        postsDao.deletePost()
    }
}