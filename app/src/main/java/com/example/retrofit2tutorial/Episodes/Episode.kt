package com.example.retrofit2tutorial.Episodes

data class Episode(
    val air_date: String,
    val characters: ArrayList<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)