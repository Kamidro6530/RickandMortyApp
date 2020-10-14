package com.example.retrofit2tutorial.main_activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit2tutorial.Characters.Character
import com.example.retrofit2tutorial.Characters.CharacterList
import com.example.retrofit2tutorial.R
import com.example.retrofit2tutorial.recyclerview.RecyclerViewAdapter
import com.example.retrofit2tutorial.retrofit.RetrofitClient
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    val list = ArrayList<Character>()
    var displayList = ArrayList<Character>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        lateinit var call: Call<CharacterList>

        for (x in 1..32) {
            call = RetrofitClient.instance.getAllCharacters("character/?page=$x")



            call.enqueue(object : Callback<CharacterList> {
                override fun onFailure(call: Call<CharacterList>, t: Throwable) {
                    Toast.makeText(
                        applicationContext,
                        "Download error check Internet",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onResponse(
                    call: Call<CharacterList>,
                    response: Response<CharacterList>
                ) {

                    if (response.isSuccessful) {
                        response.body()?.results?.forEach {
                            list.add(it)
                            displayList.add(it)

                        }

                        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                        var adapter = RecyclerViewAdapter(displayList)
                        recyclerView.adapter = adapter


                    }


                }
            })


        }
        val navigationView = findViewById<NavigationView>(R.id.navigation)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
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

    //Obsługa toolbara
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        val menuItem = menu!!.findItem(R.id.findList)
        if (menuItem != null) {
            var searchView = menuItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(query: String?): Boolean {

                    if (query!!.isNotEmpty()) {
                        var receivedText = query?.toLowerCase(Locale.getDefault())
                        displayList.clear()
                        list.forEach {

                            if (it.name.toLowerCase(Locale.getDefault())
                                    .contains(receivedText)
                            ) {
                                displayList.add(it)
                            }
                        }
                        recyclerView.adapter!!.notifyDataSetChanged()

                    } else {
                        displayList.clear()
                        displayList.addAll(list)
                        recyclerView.adapter!!.notifyDataSetChanged()
                    }
                    return true
                }
            })

        }
        return true
    }
        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            val id = item.itemId
            if (id == R.id.filterList) {
                Toast.makeText(applicationContext, "Filter clicked", Toast.LENGTH_LONG).show()


            } else {
                Toast.makeText(applicationContext, "Find clicked", Toast.LENGTH_LONG).show()
            }
            return super.onOptionsItemSelected(item)
        }


}









