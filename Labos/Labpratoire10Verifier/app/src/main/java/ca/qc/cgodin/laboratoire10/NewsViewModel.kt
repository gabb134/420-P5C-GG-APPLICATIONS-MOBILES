package ca.qc.cgodin.laboratoire10
import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import models.Article
import models.ArticleDao
import models.NewsResponse
import repository.NewsRepository

class NewsViewModel(private val newsRepository: NewsRepository): ViewModel() {

    //private val savedNewsRepository : SavedNewsRepository


    val breakingNews: MutableLiveData<NewsResponse> = MutableLiveData()

    val allNews :MutableLiveData<NewsResponse> = MutableLiveData()
    val allSavedNews: LiveData<List<Article>>

    init {
        getBreakingNews("ca")

        //val articleDao = ArticleRoomDatabase.getDatabase(application,viewModelScope).articleDao()

        //savedNewsRepository = SavedNewsRepository(articleDao)
        allSavedNews = newsRepository.allSavedNews


    }
    private fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        try {
            val response = newsRepository.getBreakingNews(countryCode)
            breakingNews.postValue(response.body())
        }catch (e: Exception){
        }
    }
     fun getSearchNews(searchQuery: String) = viewModelScope.launch {
        try {
            val response = newsRepository.getSearchNews(searchQuery)
            allNews.postValue(response.body())
        } catch (e:Exception){

        }

    }
    fun insert(article: Article) = viewModelScope.launch(Dispatchers.IO)  {
        newsRepository.insert(article)
    }



}