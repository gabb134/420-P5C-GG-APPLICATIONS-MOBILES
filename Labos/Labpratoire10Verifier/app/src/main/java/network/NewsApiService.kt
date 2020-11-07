package network

import models.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "ca",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = "4f1f6935850c473b9aaf9fc9584c742f"
    ): Response<NewsResponse>
    @GET("v2/everything")
    suspend fun getSearchNews(
        @Query("q")
        searchQuery: String,
        @Query("apiKey")
        apiKey: String =  "4f1f6935850c473b9aaf9fc9584c742f"
    ): Response<NewsResponse>


}