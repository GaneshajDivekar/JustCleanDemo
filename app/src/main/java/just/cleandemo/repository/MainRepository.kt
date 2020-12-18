package just.cleandemo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import just.cleandemo.db.PostsDao
import just.cleandemo.model.databaseclass.PostsDB
import just.cleandemo.model.postResponse.PostAPIResponse
import just.cleandemo.network.APIService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiHelper: APIService,
    private val postsDao: PostsDao
) {

    var postAPIResponseLiveData = MutableLiveData<PostAPIResponse>()

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

    fun insertData(postsDB: PostsDB) {
        postsDao.insertData(postsDB)
    }

}