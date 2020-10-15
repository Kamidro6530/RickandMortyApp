package com.example.retrofit2tutorial

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.example.retrofit2tutorial.Characters.Character
import com.example.retrofit2tutorial.Episodes.Episode
import com.example.retrofit2tutorial.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.episode_activity.*
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


class EpisodeActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.episode_activity)

       episodeNameTV.text = intent.getStringExtra("episode_name")
       airDateTV.text = intent.getStringExtra("episode_airDate")
       episodeTV.text = intent.getStringExtra("episode_episode")
       createdTV.text = intent.getStringExtra("episode_created")
        var characterNameList : ArrayList<String> = ArrayList()
        var characterObjectsList : ArrayList<Character> = ArrayList()

        lateinit var call: Call<Character>

        for (x in 0..(intent.getStringArrayListExtra("episode_characters"))!!.size){
            call = RetrofitClient.instance.getSingleCharacter("character/$x")

            call.enqueue(object : Callback<Character>{
                override fun onFailure(call: Call<Character>, t: Throwable) {
                    Log.i("TAG","Download characters in EpisodeActivity ERROR")
                }

                override fun onResponse(call: Call<Character>, response: Response<Character>) {
                    if (response.isSuccessful){
                        response.body()
                        characterNameList.add(response.body()?.name.toString())

                        characterObjectsList.add(response.body()!!)


                    }
                    var adapter =  ArrayAdapter<String>(applicationContext,R.layout.character_listview_item,characterNameList)
                    charactersListView.adapter = adapter


                    }




            })
        }
        charactersListView.setOnItemClickListener { adapterView, view, position, l ->
            val intent = Intent(applicationContext,CharacterActivity::class.java)
            intent.putExtra("image",characterObjectsList[position].image)
            intent.putExtra("name",characterObjectsList[position].name)
            intent.putExtra("gender",characterObjectsList[position].gender)
            intent.putExtra("origin",characterObjectsList[position].origin.name)
            intent.putExtra("species",characterObjectsList[position].species)
            intent.putExtra("status",characterObjectsList[position].status)
            intent.putExtra("location",characterObjectsList[position].location.name)
            startActivity(intent)
        }


        }}





/*charactersListView.setOnItemClickListener { adapterView, view, position, l ->
                        charactersListView[position].setOnContextClickListener {
                            val intent = Intent(applicationContext,CharacterActivity::class.java)
                            intent.putExtra("image",response.body()?.image)
                            intent.putExtra("name",response.body()?.name)
                            intent.putExtra("gender",response.body()?.gender)
                            intent.putExtra("origin",response.body()?.origin?.name)
                            intent.putExtra("species",response.body()?.species)
                            intent.putExtra("status",response.body()?.status)
                            intent.putExtra("location",response.body()?.location?.name)
                            startActivity(intent)
                        }*/