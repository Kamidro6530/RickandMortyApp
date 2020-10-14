package com.example.retrofit2tutorial.retrofit

import com.example.retrofit2tutorial.Characters.Character
import com.example.retrofit2tutorial.Characters.CharacterList
import com.example.retrofit2tutorial.Episodes.Episode
import com.example.retrofit2tutorial.Episodes.EpisodeList
import com.example.retrofit2tutorial.main_activities.Episodes
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface RiMService {

    @GET
    fun getAllCharacters(@Url url : String) : Call<CharacterList>

    @GET
    fun getAllEpisodes(@Url url : String) : Call<EpisodeList>

    @GET
    fun getSingleCharacter(@Url url : String) : Call<Character>


}