package just.cleandemo.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import just.cleandemo.model.databaseclass.PostsDB
import just.cleandemo.model.postResponse.PostAPIResponse
import just.cleandemo.repository.MainRepository


class MainViewModel @ViewModelInject constructor(private val mainRepository: MainRepository) : ViewModel() {

    fun putPostsData(): MutableLiveData<PostAPIResponse> {
        return mainRepository.putResponse()
    }

    fun insertInto(postsDB: PostsDB) {
        mainRepository.insertData(postsDB)
    }

}