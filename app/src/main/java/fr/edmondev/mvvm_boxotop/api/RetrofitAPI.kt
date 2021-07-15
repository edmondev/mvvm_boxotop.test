package fr.edmondev.mvvm_boxotop.api

import fr.edmondev.mvvm_boxotop.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitAPI {

    companion object {

        fun getInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.MOVIE_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

}