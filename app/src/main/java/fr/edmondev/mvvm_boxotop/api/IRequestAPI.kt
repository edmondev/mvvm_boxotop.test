package fr.edmondev.mvvm_boxotop.api

import fr.edmondev.mvvm_boxotop.model.MovieDetail
import fr.edmondev.mvvm_boxotop.model.SearchApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface IRequestAPI {

    @GET(".")
    suspend fun getMovies(@Query("apikey") apikey: String,
                          @Query("s") title: String): SearchApiResponse

    @GET(".")
    suspend fun getById(@Query("apikey") apikey: String,
                        @Query("i") imdbID: String): MovieDetail

}