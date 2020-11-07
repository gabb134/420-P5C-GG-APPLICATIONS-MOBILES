package ca.qc.cgodin.laboratoire10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.all_news_fragment.*
import repository.NewsRepository

class MainActivity : AppCompatActivity() {

    private lateinit var newsViewModel: NewsViewModel
    private val navController by lazy {
        findNavController(R.id.navHostFragmentContainer)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView.setupWithNavController(navHostFragmentContainer.findNavController())

        val newsRepository = NewsRepository()
        val viewModelProviderFactory = BreakingNewsFragment.BreakingNewsViewModelProviderFactory(newsRepository)
        try {
            val viewModelProvider = ViewModelProvider(
                navController.getViewModelStoreOwner(R.id.news_nav_graph),
                viewModelProviderFactory)
            newsViewModel = viewModelProvider.get(NewsViewModel::class.java)
        }catch (e: IllegalArgumentException){
            e.printStackTrace()
        }


    }
}