package cz.ctu.fit.bi.and.parizmat.semestral.core.data.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Named

object RetroProvider {
    private val json = Json { ignoreUnknownKeys = true }
    private const val PREFIX_JSON = "application/json"

    @OptIn(ExperimentalSerializationApi::class)
    fun dogProvider(): Retrofit = Retrofit.Builder()
        .baseUrl("https://dogapi.dog/api/v2/")
        .addConverterFactory(json.asConverterFactory(PREFIX_JSON.toMediaType()))
        .client(
            OkHttpClient.Builder()
                .addNetworkInterceptor(
                    HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)
                )
                .build()
        )
        .build()

    @OptIn(ExperimentalSerializationApi::class)
    fun imageProvider(): Retrofit = Retrofit.Builder()
        .baseUrl("https://dog.ceo/api/")
        .addConverterFactory(json.asConverterFactory(PREFIX_JSON.toMediaType()))
        .client(
            OkHttpClient.Builder()
                .addNetworkInterceptor(
                    HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)
                ).build()
        )
        .build()
}