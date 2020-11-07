package ca.qc.cgodin.laboratoire10

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import models.NewsResponse
import repository.NewsRepository

class NewsViewModel  (private val newsRepository: NewsRepository): ViewModel() {

    val breakingNews: MutableLiveData<NewsResponse> = MutableLiveData()

    init {
        getBreakingNews("ca")
    }
    private fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        try {
            val response = newsRepository.getBreakingNews(countryCode)
            breakingNews.postValue(response.body())
        }catch (e: Exception){
        }
    }


}