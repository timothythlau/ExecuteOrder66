package com.example.executeorder66.articleList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.executeorder66.network.RedditRepository
import com.example.executeorder66.network.Result
import com.example.executeorder66.network.models.RedditResponse
import kotlinx.coroutines.Dispatchers

class ListActivityViewModel(private val repository: RedditRepository) : ViewModel() {
    private val articlesLiveData by lazy {
        liveData(Dispatchers.IO) {
            emit(repository.getKotlinSubredditList())
        }
    }

    fun getList(): LiveData<Result<RedditResponse>> {
        return articlesLiveData
    }
}