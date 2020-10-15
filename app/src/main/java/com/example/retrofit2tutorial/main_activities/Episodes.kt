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
    var season1List : ArrayList<Episode> = ArrayList()
    var season2List : ArrayList<Episode> = ArrayList()
    var season3List : ArrayList<Episode> = ArrayList()
    var season4List : ArrayList<Episode> = ArrayList()
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

                          if (it.episode.contains("S01"))
                              season1List.add(it)
                          if (it.episode.contains("S02"))
                              season2List.add(it)
                          if (it.episode.contains("S03"))
                              season3List.add(it)
                          if (it.episode.contains("S04"))
                              season4List.add(it)
                      }

                    }
                    //Dodaje elementy do listy dopiero za ostatnim "obrotem" żeby nie dodawać tych samych obiektów 5 razy
                    if (x == 5){
                        list.add(Episode("", ArrayList(), "","",0,"Season 1",""))
                        list.addAll(season1List)
                        list.add(Episode("",ArrayList(),"","",0,"Season 2",""))
                        list.addAll(season2List)
                        list.add(Episode("",ArrayList(),"","",0,"Season 3",""))
                        list.addAll(season3List)
                        list.add(Episode("",ArrayList(),"","",0,"Season 4",""))
                        list.addAll(season4List)
                        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                        var adapter = EpisodeRecyclerAdapter(list)
                        recyclerView.adapter = adapter
                    }
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