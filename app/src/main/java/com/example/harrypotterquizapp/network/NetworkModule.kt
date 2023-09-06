package com.example.harrypotterquizapp.network

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://harry-potter-api.onrender.com/db"

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideApi(): Api {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(Api::class.java)
    }

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth
}