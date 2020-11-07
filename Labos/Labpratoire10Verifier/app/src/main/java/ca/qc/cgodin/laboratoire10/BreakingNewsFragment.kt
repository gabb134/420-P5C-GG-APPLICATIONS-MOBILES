package ca.qc.cgodin.laboratoire10

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import kotlinx.android.synthetic.main.breaking_news_fragment.*
import repository.NewsRepository

class BreakingNewsFragment : Fragment() {
    private lateinit var newsAdapter: NewsAdapter

    private val newsViewModel: NewsViewModel by
    navGraphViewModels(R.id.news_nav_graph)
    companion object {
        fun newInstance() = BreakingNewsFragment()
    }

    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.breaking_news_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      //  val newsRepository = NewsRepository()
       // val viewModelProviderFactory = BreakingNewsViewModelProviderFactory(newsRepository)
       // viewModel = ViewModelProvider(
      //      viewModelProviderFactory
      //  ).get(NewsViewModel::class.java)
        newsAdapter = NewsAdapter()
        rvBreakingNews.adapter = newsAdapter
        newsViewModel.breakingNews.observe(
            viewLifecycleOwner,
            Observer { newsResponse ->
                newsAdapter.setArticles(newsResponse.articles)
            }
        )


    }

    class BreakingNewsViewModelProviderFactory(
        private val newsRepository: NewsRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
                NewsViewModel(newsRepository) as T
            } else {
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
      //  viewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        // TODO: Use the ViewModel

        newsAdapter.setOnItemClickListener {
            Log.i("Labo10", "You have clicked on me!!")
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_breakingNewsFragment_to_articleFragment,
                bundle
            )
        }
    }

}