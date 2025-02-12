package ro.si.newsapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository {
    private val BASE_URL = "https://newsapi.org/"
    private val apiService: NewsApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(NewsApiService::class.java)
    }

    // Now we accept a country parameter
    fun getTopHeadlines(apiKey: String, sources: String) = apiService.getTopHeadlines(sources, apiKey)
}