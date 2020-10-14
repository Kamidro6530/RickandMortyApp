package com.example.retrofit2tutorial

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.character_activity.*

class CharacterActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_activity)

        Picasso.get().load(intent.getStringExtra("image")).into(characterIV)

        characterNameTV.text = intent.getStringExtra("name")
        characterGenderTV.text = "Gender : "+intent.getStringExtra("gender")
        characterOriginTV.text = "Origin : "+intent.getStringExtra("origin")
        characterSpeciesTV.text = "Species : "+intent.getStringExtra("species")
        characterStatusTV.text = "Status : "+intent.getStringExtra("status")
        characterlocationTV.text = "Location : "+intent.getStringExtra("location")

    }
}