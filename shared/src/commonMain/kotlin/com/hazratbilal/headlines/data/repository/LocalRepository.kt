package com.hazratbilal.headlines.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.hazratbilal.headlines.data.local.db.HeadlinesDatabase
import com.hazratbilal.headlines.data.model.Article
import com.hazratbilal.headlines.data.model.Source
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class LocalRepository(private val database: HeadlinesDatabase) {

    fun getAllBookmarks(): Flow<List<Article>> {
        return database.articleEntityQueries
            .getAllArticles()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { entities ->
                entities.map { entity ->
                    Article(
                        url = entity.url,
                        title = entity.title,
                        description = entity.description,
                        urlToImage = entity.urlToImage,
                        publishedAt = entity.publishedAt,
                        content = entity.content,
                        author = entity.author,
                        source = Source(id = "", name = entity.sourceName)
                    )
                }
            }
    }

    suspend fun insertBookmark(article: Article) = withContext(Dispatchers.IO) {
        database.articleEntityQueries.insertArticle(
            url = article.url,
            title = article.title,
            description = article.description,
            urlToImage = article.urlToImage,
            publishedAt = article.publishedAt,
            content = article.content,
            author = article.author,
            sourceName = article.source.name
        )
    }

    suspend fun deleteBookmark(url: String) = withContext(Dispatchers.IO) {
        database.articleEntityQueries.deleteArticle(url)
    }

    suspend fun deleteAllBookmarks() = withContext(Dispatchers.IO) {
        database.articleEntityQueries.deleteAllArticles()
    }

    suspend fun isBookmarked(url: String): Boolean = withContext(Dispatchers.IO) {
        database.articleEntityQueries.isBookmarked(url).executeAsOne() > 0
    }
}