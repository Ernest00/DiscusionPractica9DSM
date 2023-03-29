package com.example.github_api_app

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("/users/{username}")
    fun getUser(@Path("username") username: String): Call<User>

    @GET("/users/{username}/repos")
    fun getRepos(@Path("username") username: String): Call<List<Repository>>

}