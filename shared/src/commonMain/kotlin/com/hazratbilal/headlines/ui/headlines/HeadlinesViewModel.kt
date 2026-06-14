package com.hazratbilal.headlines.ui.headlines

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.hazratbilal.headlines.data.model.Article
import com.hazratbilal.headlines.data.remote.RemoteRepository
import com.hazratbilal.headlines.utils.Resource
import com.hazratbilal.headlines.utils.categoryList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HeadlinesViewModel(
    private val remoteRepository: RemoteRepository
) : ViewModel() {

    private val log = Logger.withTag("HeadlinesViewModel")

    private val _articlesStateFlow = MutableStateFlow<Resource<List<Article>>>(Resource.Loading)
    val articlesStateFlow: StateFlow<Resource<List<Article>>> get() = _articlesStateFlow

    var selectedCategory by mutableStateOf(categoryList[0])

    init {
        getHeadlines(selectedCategory)
    }

    fun getHeadlines(category: String = selectedCategory) {
        viewModelScope.launch(Dispatchers.IO) {
            _articlesStateFlow.emit(Resource.Loading)
            log.d { "Fetching headlines for: $category" }

            try {
                when (val result = remoteRepository.getNews(category)) {
                    is Resource.Success -> {
                        log.d { "Articles loaded: ${result.data.articles.size}" }
                        _articlesStateFlow.emit(Resource.Success(result.data.articles))
                    }
                    is Resource.Error -> {
                        log.e { "Error: ${result.message}" }
                        _articlesStateFlow.emit(Resource.Error(result.message))
                    }
                    is Resource.Loading -> Unit
                    is Resource.Idle -> Unit
                }
            } catch (ex: Exception) {
                _articlesStateFlow.emit(Resource.Error(ex.message.toString()))
            }
        }
    }
}