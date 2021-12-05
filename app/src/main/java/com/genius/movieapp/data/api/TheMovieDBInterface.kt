package com.recepyesilkaya.moviemvvm.data.api

import com.recepyesilkaya.moviemvvm.data.vo.MovieDetails
import com.recepyesilkaya.moviemvvm.data.vo.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBInterface {


    @GET("movie/popular")
    fun getPopularMovie(@Query("page") page: Int): Single<MovieResponse>

    @GET("movie/{movie_id}")
    fun getData(@Path("movie_id") id :Int): Single<MovieDetails>
}