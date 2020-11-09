package ca.qc.cgodin.laboratoire10

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
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



        //val fab: FloatingActionButton =  ArticleFragment.findViewById(R.id.fab)



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
            //Envoie les donnes vers articlefragment
            /*val intent = Intent(holderArticle.tvTitle.context, ArticleFragment::class.java)

            intent.putExtra("author", article.author)
            intent.putExtra("content", article.content)
            intent.putExtra("description", article.description)
            intent.putExtra("publishedAt", article.publishedAt)
            intent.putExtra("source", article.source.toString())
            intent.putExtra("title", article.title)
            intent.putExtra("url", article.url)
            intent.putExtra("urlToImage", article.urlToImage)


            /*val bundle = Bundle()
            bundle.putString("your_key", "your_value")
            ArticleFragment.*/

            // Toast.makeText(holderArticle.tvTitle.context, "title dans le adapter : ${article.title}", Toast.LENGTH_SHORT).show()

            Log.i("TAG", "title dans le adapter : ${article.title}")*/
        }
       /* holderArticle.fab.setOnClickListener {
            Toast.makeText(holderArticle.tvTitle.context, "title dans le adapter : ${article.title}", Toast.LENGTH_SHORT).show()
        }*/





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