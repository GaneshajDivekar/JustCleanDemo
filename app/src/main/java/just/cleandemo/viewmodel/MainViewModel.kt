package just.cleandemo.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import just.cleandemo.model.databaseclass.PostsDB
import just.cleandemo.model.postResponse.PostAPIResponse
import just.cleandemo.model.postcommentResponse.PostCommentResponse
import just.cleandemo.repository.MainRepository


class MainViewModel @ViewModelInject constructor(private val mainRepository: MainRepository) : ViewModel() {

    fun putPostsData(): MutableLiveData<PostAPIResponse> {
        return mainRepository.putResponse()
    }

    fun getCommentResponse(ID: String?): MutableLiveData<PostCommentResponse> {
        return mainRepository.getCommentResponse(ID!!.toInt())
    }

    fun insertPost(postsDB: PostsDB) {
        mainRepository.insertData(postsDB)
    }
    fun getPostsInfo():LiveData<List<PostsDB>>  {
        return mainRepository.getPostsData()
    }
    fun getPostsInfoo():LiveData<List<PostsDB>>  {
        return mainRepository.getPostsDataa()
    }
    fun getFavouritePostsInfo(favourite_id:String):LiveData<List<PostsDB>>  {
        return mainRepository.getFavouriteData(favourite_id)
    }

    fun updateStatusPost(ID: String?, favFlag: String) {
          mainRepository.updateStatus(ID!!,favFlag)
    }


}