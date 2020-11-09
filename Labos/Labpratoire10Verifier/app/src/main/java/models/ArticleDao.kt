package models

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ArticleDao {
    @Query("SELECT * from article_table ORDER BY title ASC")
    fun getArticles(): LiveData<List<Article>>

    @Query("SELECT * FROM article_table WHERE url=(:url)")
    fun getArticle(url: String): LiveData<Article?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(article: Article)

    @Update
    fun updateArticle(article: Article)

    @Query("DELETE FROM article_table")
    fun deleteAll()

    @Query("DELETE FROM article_table WHERE url=(:url)")
    fun deleteArticle(url: String)

}