package com.genius.movieapp.data.api

import com.genius.movieapp.data.vo.MovieDetails
import com.genius.movieapp.data.vo.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBInterface {

    @GET("movie/top_rated")
    fun getPopularMovie(@Query("page") page: Int): Single<MovieResponse>

    @GET("movie/{movie_id}")
    fun getData(@Path("movie_id") id :Int): Single<MovieDetails>
}