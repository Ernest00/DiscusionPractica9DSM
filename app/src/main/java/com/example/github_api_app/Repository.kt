package com.example.github_api_app

data class Repository(
    val name: String,
    val description: String?,
    val stargazers_count: Int,
    val forks_count: Int
)
