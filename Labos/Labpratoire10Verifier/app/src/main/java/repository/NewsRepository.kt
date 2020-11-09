package repository

import models.Article
import models.ArticleDao
import network.RetrofitInstance

class NewsRepository( ) {

    suspend fun getBreakingNews(countryCode: String) =
        RetrofitInstance.retrofitService.getBreakingNews(countryCode)

    suspend fun getSearchNews(searchQuery: String) =
        RetrofitInstance.retrofitService.getSearchNews(searchQuery)


}