package ca.qc.cgodin.laboratoire10

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import kotlinx.android.synthetic.main.all_news_fragment.*
import kotlinx.android.synthetic.main.breaking_news_fragment.*

class AllNewsFragment : Fragment() {
    private lateinit var newsAdapter: NewsAdapter

    private val newsViewModel: NewsViewModel by
    navGraphViewModels(R.id.news_nav_graph)
    companion object {
        fun newInstance() = AllNewsFragment()
    }

    private lateinit var viewModel: AllNewsViewModel




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.all_news_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsAdapter = NewsAdapter()
        rvAllNews.adapter = newsAdapter
        newsViewModel.breakingNews.observe(
            viewLifecycleOwner,
            Observer { newsResponse ->
                newsAdapter.setArticles(newsResponse.articles)
            }
        )
        btnRechercher.setOnClickListener {
            Toast.makeText(context, "bouton rechercher est cliqué!", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
      //  viewModel = ViewModelProviders.of(this).get(AllNewsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}