package com.genius.movieapp.ui.single_movie_details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.genius.movieapp.R
import com.genius.movieapp.data.api.POSTER_BASE_URL
import com.genius.movieapp.data.api.TheMovieDBClient
import com.genius.movieapp.data.api.TheMovieDBInterface
import com.genius.movieapp.data.vo.MovieDetails
import kotlinx.android.synthetic.main.activity_single.*


class SingleActivity : AppCompatActivity() {

    private lateinit var viewModel:SingleMovieViewModel
    private  lateinit var movieRepository:MovieDetailsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)

        var movieId:Int=intent.getIntExtra("id",1)
        println("id "+movieId)

        val apiService: TheMovieDBInterface=TheMovieDBClient.getClient()
        movieRepository= MovieDetailsRepository(apiService)

        viewModel=getViewModel(movieId)

        viewModel.movieDetails.observe(this, Observer {
            bindUI(it)
        })

        viewModel.networkState.observe(this, Observer {

        })

    }

    private fun bindUI(it: MovieDetails?) {
        if (it != null) {
            movie_title.text = it.title

            movie_overview.text = it.overview

            var moviePosterURL = POSTER_BASE_URL + it.posterPath
            Glide.with(this)
                .load(moviePosterURL)
                .into(iv_movie_poster)
        }

    }


    private fun getViewModel(movieId:Int) : SingleMovieViewModel{
        return ViewModelProviders.of(this, object :ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return SingleMovieViewModel(movieRepository,movieId)as T
            }

        })[SingleMovieViewModel::class.java]
    }

}
