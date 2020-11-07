package ca.qc.cgodin.laboratoire10

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.navGraphViewModels

class SavedNewsFragment : Fragment() {
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SavedNewsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}