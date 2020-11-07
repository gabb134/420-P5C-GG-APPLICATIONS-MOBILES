package ca.qc.cgodin.laboratoire10

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.news_item.view.*
import models.Article

class NewsAdapter() : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {
    private var articles: List<Article> = emptyList()
    private lateinit var onItemClickListener: ((Article) -> Unit)
    private lateinit var context: Context

    inner class ArticleViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val ivCoverImage: ImageView = itemView.findViewById(R.id.ivCoverImage)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)

        override fun toString(): String {
            return super.toString() + " '" + tvTitle.text + "'"
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ArticleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item, parent, false)
        return ArticleViewHolder(view)
    }
    override fun onBindViewHolder(holderArticle: ArticleViewHolder, position: Int)
    {
        val article = articles[position]
        holderArticle.tvTitle.text = article.title
        holderArticle.itemView.apply {

            Glide.with(this).load(article.urlToImage).into(holderArticle.ivCoverImage)
            tvTitle.text = article.title
        }

        holderArticle.itemView.setOnClickListener {
            onItemClickListener(article)
        }





    }
    override fun getItemCount(): Int = articles.size

    fun setArticles(articles: List<Article>) {
        this.articles = articles
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (Article) -> Unit){
        onItemClickListener = listener
    }



}