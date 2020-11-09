package ca.qc.cgodin.laboratoire10

import android.content.Intent.getIntent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import kotlinx.android.synthetic.main.article_fragment.*
import kotlinx.android.synthetic.main.article_fragment.fab
import kotlinx.android.synthetic.main.article_fragment.view.*

class ArticleFragment : Fragment() {

    private val args: ArticleFragmentArgs by navArgs()

   // private lateinit var webView: WebView


    private val newsViewModel: NewsViewModel by
    navGraphViewModels(R.id.news_nav_graph)
    companion object {
        fun newInstance() = ArticleFragment()
    }

    private lateinit var viewModel: ArticleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.article_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // webView = requireView().findViewById(R.id.webView)




        val article = args.article
        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)

          //  Toast.makeText(context, "nom du titre : ${article.title}", Toast.LENGTH_SHORT).show()
        }
        fab.setOnClickListener {
             //newsViewModel.insert(article)
            Toast.makeText(context, "Cet article a été ajouté dans la base de données!", Toast.LENGTH_SHORT).show()
        }


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProviders.of(this).get(ArticleViewModel::class.java)
        // TODO: Use the ViewModel
    }

}