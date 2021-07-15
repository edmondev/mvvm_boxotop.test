package fr.edmondev.mvvm_boxotop.ui

import android.os.Bundle
import android.view.Menu
import android.view.WindowManager
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.edmondev.mvvm_boxotop.R
import fr.edmondev.mvvm_boxotop.viewmodel.MovieListActivityViewModel

class MovieListActivity : AppCompatActivity() {

    private lateinit var recyclerAdapter : MovieListAdapter
    private lateinit var viewModel: MovieListActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //edbab: reset default theme after splash
        setTheme(R.style.Theme_Boxotop)
        //edbab: and before main activity set
        setContentView(R.layout.activity_movie_list)

        initViewAdapter()
        initViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.movie_list_menu, menu)
        val searchMenu = menu?.findItem(R.id.search_movies)
        val searchView = searchMenu?.actionView as SearchView
        //searchView.queryHint = "@string/"

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                //edbab: TODO: check to hide keyboard
                //window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
                //edbab: submit query string to api
                query?.let { viewModel.getMovies(it.trim()) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //edbab: can be used for result filtering ...
                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    /**
     * Init RecyclerView Adapter
     */
    private fun initViewAdapter() {
        val recyclerView = findViewById<RecyclerView>(R.id.content)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)

        recyclerAdapter = MovieListAdapter()
        recyclerView.adapter = recyclerAdapter
    }

    /**
     * Init ViewModel Observer
     */
    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MovieListActivityViewModel::class.java)

        viewModel.getRecyclerListObserver().observe(this, {
            if (it != null) {
                recyclerAdapter.updateDataSet(it)
            } else { // Data error !
                Toast.makeText(this, "No data found : Please try again ...", Toast.LENGTH_LONG).show()
            }
        })
    }

}