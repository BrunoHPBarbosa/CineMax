package com.devspacecinenow.list.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devspacecinenow.common.data.RetrofitBuilder
import com.devspacecinenow.common.model.MovieDto
import com.devspacecinenow.common.model.MovieResponse
import com.devspacecinenow.list.data.ListService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Response

class MovieListViewModel(

     listServive : ListService
): ViewModel() {

    private val _uiNowPlaying = MutableStateFlow<List<MovieDto>>(emptyList())
    val uiNowPlaying: StateFlow<List<MovieDto>> = _uiNowPlaying

    private val _uiTopRated = MutableStateFlow<List<MovieDto>>(emptyList())
    val uiTopRated: StateFlow<List<MovieDto>> = _uiTopRated

    private val _uiUpcomming = MutableStateFlow<List<MovieDto>>(emptyList())
    val uiUpcomming : StateFlow<List<MovieDto>> = _uiUpcomming

    private val _uiPopular = MutableStateFlow<List<MovieDto>>(emptyList())
    val uiPopular : StateFlow<List<MovieDto>> = _uiPopular

    init{

        listServive.popularMovies().enqueue(object : retrofit2.Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                if (response.isSuccessful) {
                    val movies = response.body()?.results
                    if (movies != null) {

                        _uiPopular.value = movies
                    }
                } else {
                    Log.d("MovieListViewModel", "request Error :: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d("MovieListViewModel", "NetWork Error :: ${t.message}")
            }

        })
    }

    init{

        listServive.UpComingMovies().enqueue(object : retrofit2.Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                if (response.isSuccessful) {
                    val movies = response.body()?.results
                    if (movies != null) {

                        _uiUpcomming.value = movies
                    }
                } else {
                    Log.d("MovieListViewModel", "request Error :: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d("MovieListViewModel", "NetWork Error :: ${t.message}")
            }

        })
    }

    init {
       listServive.topRatedMovies().enqueue(object : retrofit2.Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                if (response.isSuccessful) {
                    val movies = response.body()?.results
                    if (movies != null) {

                        _uiTopRated.value = movies
                    }
                } else {
                    Log.d("MovieListViewModel", "request Error :: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d("MovieListViewModel", "NetWork Error :: ${t.message}")
            }

        })

    }

    init {
        listServive.nowPlaying().enqueue(object : retrofit2.Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                if (response.isSuccessful) {
                    val movies = response.body()?.results
                    if (movies != null) {

                        _uiNowPlaying.value = movies
                    }
                } else {
                    Log.d("MovieListViewModel", "request Error :: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d("MovieListViewModel", "NetWork Error :: ${t.message}")
            }

        })
    }

    companion object{
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val listService = RetrofitBuilder.retofitInstance.create(ListService::class.java)
                return MovieListViewModel(listService) as T
            }
        }
    }
}