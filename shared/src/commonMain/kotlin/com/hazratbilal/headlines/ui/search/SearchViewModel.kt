package com.hazratbilal.headlines.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.hazratbilal.headlines.data.model.Article
import com.hazratbilal.headlines.data.remote.RemoteRepository
import com.hazratbilal.headlines.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val remoteRepository: RemoteRepository
) : ViewModel() {

    private val log = Logger.withTag("SearchViewModel")
    private val _articlesStateFlow = MutableStateFlow<Resource<List<Article>>>(Resource.Idle)
    val articlesStateFlow: StateFlow<Resource<List<Article>>> get() = _articlesStateFlow

    fun searchHeadlines(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _articlesStateFlow.emit(Resource.Loading)
            log.d { "Fetching searches for: $query" }

            try {
                when (val result = remoteRepository.searchNews(query)) {
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