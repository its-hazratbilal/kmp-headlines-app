package com.hazratbilal.headlines.ui.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hazratbilal.headlines.data.model.Article
import com.hazratbilal.headlines.data.repository.LocalRepository
import com.hazratbilal.headlines.utils.Resource
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class BookmarkViewModel(
    private val localRepository: LocalRepository
) : ViewModel() {

    val bookmarks = localRepository.getAllBookmarks()
        .map { articles ->
            if (articles.isEmpty()) Resource.Error("No bookmarks yet")
            else Resource.Success(articles)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = Resource.Loading
        )
}