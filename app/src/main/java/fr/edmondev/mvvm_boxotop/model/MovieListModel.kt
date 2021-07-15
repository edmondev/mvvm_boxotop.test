package fr.edmondev.mvvm_boxotop.model

import com.google.gson.annotations.SerializedName

data class SearchApiResponse(
    @SerializedName("Response")     val response: Boolean,
    @SerializedName("Error")        val error: String?,
    @SerializedName("Search")       val items: List<MovieListItem>?, //MovieList?,
    @SerializedName("totalResults") val item_count: String?
)

//data class MovieList(val items: ArrayList<MovieListItem>)

data class MovieListItem(
    @SerializedName("imdbID")   val id: String,
    @SerializedName("Title")    val title: String,
    @SerializedName("Type")     val type: String,
    @SerializedName("Poster")   val poster_url: String,
    @SerializedName("Year")     val year: String
)