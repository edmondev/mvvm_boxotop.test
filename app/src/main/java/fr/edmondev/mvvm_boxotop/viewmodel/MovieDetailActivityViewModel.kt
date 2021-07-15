package fr.edmondev.mvvm_boxotop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.edmondev.mvvm_boxotop.BuildConfig
import fr.edmondev.mvvm_boxotop.api.IRequestAPI
import fr.edmondev.mvvm_boxotop.api.RetrofitAPI
import fr.edmondev.mvvm_boxotop.model.MovieDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailActivityViewModel: ViewModel() {

    private var movieDetailLiveData: MutableLiveData<MovieDetail> = MutableLiveData()

    fun getDetailObserver(): MutableLiveData<MovieDetail> {
        return movieDetailLiveData
    }

    fun getById(imdbID: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val retrofitAPI = RetrofitAPI.getInstance().create(IRequestAPI::class.java)
            val response = retrofitAPI.getById(BuildConfig.MOVIE_API_KEY, imdbID)
            //edbab: TODO: handle errors (too many result, empty result ...)
            with(movieDetailLiveData) {
                postValue(response)
            }
        }
    }
}