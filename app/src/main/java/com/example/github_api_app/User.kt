package com.example.github_api_app

data class User(
    val login: String,
    val name: String,
    val avatar_url: String,
    val publicRepos: Int
)