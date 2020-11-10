package ca.qc.cgodin.laboratoire10

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import kotlinx.android.synthetic.main.all_news_fragment.*
import kotlinx.android.synthetic.main.saved_news_fragment.*

class SavedNewsFragment : Fragment() {
    private lateinit var newsAdapter: NewsAdapter
    private val newsViewModel: NewsViewModel by
    navGraphViewModels(R.id.news_nav_graph)
    companion object {
        fun newInstance() = SavedNewsFragment()
    }

    private lateinit var viewModel: SavedNewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.saved_news_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsAdapter = NewsAdapter()
        rvSavedNews.adapter = newsAdapter
        newsViewModel.allSavedNews.observe(
            viewLifecycleOwner, Observer {
                    articles ->
                newsAdapter.setArticles(articles)
            }
        )


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        newsAdapter.setOnItemClickListener {
            Log.i("Labo10", "You have clicked on me!!")
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_savedNewsFragment_to_articleFragment,
                bundle
            )
        }
       // viewModel = ViewModelProviders.of(this).get(SavedNewsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}