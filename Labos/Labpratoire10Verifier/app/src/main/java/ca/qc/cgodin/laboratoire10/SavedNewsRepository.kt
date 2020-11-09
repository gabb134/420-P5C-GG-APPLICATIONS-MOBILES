package ca.qc.cgodin.laboratoire10

import models.Article
import models.ArticleDao

class SavedNewsRepository(private val articleDao: ArticleDao) {

    suspend fun insert(article: Article) {
        articleDao.insert(article)
    }

}