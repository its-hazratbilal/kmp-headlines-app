package com.hazratbilal.headlines.data.remote

import com.hazratbilal.headlines.data.model.ErrorResponse
import com.hazratbilal.headlines.data.model.NewsResponse
import com.hazratbilal.headlines.utils.API_KEY
import com.hazratbilal.headlines.utils.Resource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class RemoteRepository(private val httpClient: HttpClient) {

    suspend fun getNews(category: String): Resource<NewsResponse> {
        return try {
            val response = httpClient.get("everything") {
                parameter("q", category)
                parameter("apiKey", API_KEY)
            }
            if (response.status.value in 200..299) {
                Resource.Success(response.body())
            } else {
                Resource.Error(response.body<ErrorResponse>().message)
            }
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    suspend fun searchNews(query: String): Resource<NewsResponse> {
        return try {
            val response = httpClient.get("everything") {
                if (query.isNotEmpty()) {
                    parameter("q", query)
                }
                parameter("apiKey", API_KEY)
            }
            if (response.status.value in 200..299) {
                Resource.Success(response.body())
            } else {
                Resource.Error(response.body<ErrorResponse>().message)
            }
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }
}