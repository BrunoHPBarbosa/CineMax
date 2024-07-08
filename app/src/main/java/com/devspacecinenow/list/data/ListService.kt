package com.devspacecinenow.list.data

import com.devspacecinenow.common.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface ListService {

    @GET("now_playing?language=en-US&page=1")
    fun nowPlaying() : Call<MovieResponse>

    @GET("top_rated?language=en-US&page=1")
    fun topRatedMovies(): Call<MovieResponse>

    @GET("upcoming?language=en-US&page=1")
    fun UpComingMovies(): Call<MovieResponse>

    @GET("popular?language=en-US&page=")
    fun popularMovies(): Call<MovieResponse>
}