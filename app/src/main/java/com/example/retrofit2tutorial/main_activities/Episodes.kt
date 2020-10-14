package com.example.retrofit2tutorial.main_activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit2tutorial.Episodes.Episode
import com.example.retrofit2tutorial.Episodes.EpisodeList
import com.example.retrofit2tutorial.R
import com.example.retrofit2tutorial.recyclerview.EpisodeRecyclerAdapter
import com.example.retrofit2tutorial.recyclerview.RecyclerViewAdapter
import com.example.retrofit2tutorial.retrofit.RetrofitClient
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Episodes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.episodes)
        lateinit var call : Call<EpisodeList>
        val list  = ArrayList<Episode>()
        for (x in 1..5){
            call = RetrofitClient.instance.getAllEpisodes("episode?page=$x")

            call.enqueue(object  : Callback<EpisodeList> {
                override fun onFailure(call: Call<EpisodeList>, t: Throwable) {
                    Log.i("TAG","Episode download error")
                }

                override fun onResponse(call: Call<EpisodeList>, response: Response<EpisodeList>) {
                    if (response.isSuccessful){
                      response.body()?.results?.forEach {

                          list.add(it)
                      }
                    }
                    recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                    var adapter = EpisodeRecyclerAdapter(list)
                    recyclerView.adapter = adapter
                    }

            }

            )}


        val navigationView = findViewById<NavigationView>(R.id.navigation)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.menu_characters -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }

                R.id.menu_episodes -> {
                    val intent = Intent(this, Episodes::class.java)
                    startActivity(intent)
                }


            }
            true
        }
    }
}