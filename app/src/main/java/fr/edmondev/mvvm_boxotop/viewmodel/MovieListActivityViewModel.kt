package fr.edmondev.mvvm_boxotop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.edmondev.mvvm_boxotop.BuildConfig
import fr.edmondev.mvvm_boxotop.api.IRequestAPI
import fr.edmondev.mvvm_boxotop.api.RetrofitAPI
import fr.edmondev.mvvm_boxotop.model.MovieListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieListActivityViewModel: ViewModel() {

    private var movieListLiveData: MutableLiveData<List<MovieListItem>> = MutableLiveData()

    fun getRecyclerListObserver(): MutableLiveData<List<MovieListItem>> {
        return movieListLiveData
    }

    fun getMovies(search: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val retrofitAPI = RetrofitAPI.getInstance().create(IRequestAPI::class.java)
            val response = retrofitAPI.getMovies(BuildConfig.MOVIE_API_KEY, search)
            //edbab: TODO: handle errors (too many result, empty result ...)
            with(movieListLiveData) {
                postValue(response.items)
            }

        }
    }
}