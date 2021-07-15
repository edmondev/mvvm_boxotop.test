package fr.edmondev.mvvm_boxotop.model

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @SerializedName("Response") val response: Boolean,
    @SerializedName("imdbID")   val imdb_id: String,
    @SerializedName("Title")    val title: String,
    @SerializedName("Type")     val type: String,
    @SerializedName("Poster")   val poster_url: String,
    @SerializedName("Year")     val year: String,
    @SerializedName("Rated")    val rated: String,
    @SerializedName("Released") val released: String,
    @SerializedName("Runtime")  val runtime: String,
    @SerializedName("Genre")    val genre: String,
    @SerializedName("Director") val director: String,
    @SerializedName("Writer")   val writer: String,
    @SerializedName("Actors")   val actors: String,
    @SerializedName("Plot")     val plot: String,
    @SerializedName("Language") val language: String,
    @SerializedName("Country")  val country: String,
    @SerializedName("Awards")   val awards: String,
    @SerializedName("imdbRating") val imdb_rating: String,
    @SerializedName("totalSeasons") val total_seasons: String?
)