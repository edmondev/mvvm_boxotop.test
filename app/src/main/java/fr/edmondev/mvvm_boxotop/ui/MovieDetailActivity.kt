package fr.edmondev.mvvm_boxotop.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import fr.edmondev.mvvm_boxotop.R
import fr.edmondev.mvvm_boxotop.model.MovieDetail
import fr.edmondev.mvvm_boxotop.viewmodel.MovieDetailActivityViewModel
import fr.edmondev.mvvm_boxotop.viewmodel.MovieListActivityViewModel
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {
    private var imdbId: String = ""
    private lateinit var viewModel: MovieDetailActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //edbab: reset default theme after splash
        setTheme(R.style.Theme_Boxotop)
        //edbab: and before main activity set
        setContentView(R.layout.activity_movie_detail)

        actionBar?.setDisplayHomeAsUpEnabled(true)
        imdbId = intent.getStringExtra("imdbId")!!

        initViewModel()
    }

    /**
     * Init ViewModel Observer
     */
    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MovieDetailActivityViewModel::class.java)
        imdbId?.let {
            viewModel.getById(imdbId)
            viewModel.getDetailObserver().observe(this, {
                if (it != null) {
                    updateUI(it)
                } else { // Data error !
                    Toast.makeText(this, "No data found ! Go back ...", Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    private fun updateUI(detail: MovieDetail) {
        Picasso.get().load(detail.poster_url).fit().into(movie_image)
        this.title = detail.title
        title_textview.text = "${detail.type.uppercase()} - ${detail.released}"
        infos_textview.text = "${detail.country}\n${detail.genre}\nRuntime: ${detail.runtime}\nRating: ${detail.imdb_rating}"
        actors_textview.text = "With: ${detail.actors}"
        awards_textview.text = "Awards: ${detail.awards}"
        plot_textview.text = detail.plot

    }
}