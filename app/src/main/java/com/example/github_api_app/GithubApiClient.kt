package com.example.github_api_app

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GithubApiClient {

    private const val BASE_URL = "https://api.github.com"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val githubApi = retrofit.create(GithubApi::class.java)

    fun getUser(username: String): Call<User> {
        return githubApi.getUser(username)
    }

    fun getRepos(username: String): Call<List<Repository>> {
        return githubApi.getRepos(username)
    }

}