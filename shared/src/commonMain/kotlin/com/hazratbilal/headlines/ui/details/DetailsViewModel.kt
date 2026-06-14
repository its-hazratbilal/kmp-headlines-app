package com.hazratbilal.headlines.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hazratbilal.headlines.data.model.Article
import com.hazratbilal.headlines.data.repository.LocalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val localRepository: LocalRepository
) : ViewModel() {

    private val _isBookmarked = MutableStateFlow(false)
    val isBookmarked: StateFlow<Boolean> = _isBookmarked.asStateFlow()

    fun checkBookmark(url: String) {
        viewModelScope.launch {
            _isBookmarked.value = localRepository.isBookmarked(url)
        }
    }

    fun toggleBookmark(article: Article) {
        viewModelScope.launch {
            if (_isBookmarked.value) {
                localRepository.deleteBookmark(article.url)
                _isBookmarked.value = false
            } else {
                localRepository.insertBookmark(article)
                _isBookmarked.value = true
            }
        }
    }
}