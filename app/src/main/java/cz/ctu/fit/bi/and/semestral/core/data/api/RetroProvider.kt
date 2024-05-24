package cz.ctu.fit.bi.and.semestral.core.data.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object RetroProvider {
    private val json = Json { ignoreUnknownKeys = true }
    @OptIn(ExperimentalSerializationApi::class)
    fun dogProvider() = Retrofit.Builder()
        .baseUrl("https://dogapi.dog/api/v2/")
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .client(
            OkHttpClient.Builder()
                .addNetworkInterceptor(
                    HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        )
        .build()
}