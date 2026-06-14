package com.hazratbilal.headlines.utils

import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.network.ktor3.KtorNetworkFetcherFactory
import coil3.request.crossfade
import io.ktor.client.HttpClient
import io.ktor.client.engine.java.Java

actual fun initCoil() {
    SingletonImageLoader.setSafe {
        ImageLoader.Builder(PlatformContext.INSTANCE)
            .components {
                add(KtorNetworkFetcherFactory(
                    httpClient = { HttpClient(Java) }
                ))
            }
            .crossfade(true)
            .build()
    }
}