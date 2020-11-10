package repository

import androidx.lifecycle.LiveData
import models.Article
import models.ArticleDao
import network.RetrofitInstance

class NewsRepository( private val articleDao:ArticleDao) {

    val allSavedNews : LiveData<List<Article>> = articleDao.getAllArticles()

    suspend fun getBreakingNews(countryCode: String) =
        RetrofitInstance.retrofitService.getBreakingNews(countryCode)

    suspend fun getSearchNews(searchQuery: String) =
        RetrofitInstance.retrofitService.getSearchNews(searchQuery)

    suspend fun insert(article: Article){
        articleDao.insert(article)
    }

    //fun getSavecNews() = RetrofitInstance.retrofitService.g

}
