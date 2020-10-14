package com.example.retrofit2tutorial.recyclerview

import android.content.Intent
import android.graphics.Color.rgb
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit2tutorial.CharacterActivity
import com.example.retrofit2tutorial.Characters.Character
import com.example.retrofit2tutorial.R
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(private val list : ArrayList<Character>): RecyclerView.Adapter<CharacterHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        var inflater = LayoutInflater.from(parent.context)
       var view =  inflater.inflate(R.layout.character_item,parent,false)
        return CharacterHolder(view)
    }

    override fun getItemCount(): Int {
         return list.size
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        holder.name.text = list[position].name
        holder.species.text = list[position].species
        if (list[position].status == "Alive"){
            holder.status.text = list[position].status
        holder.status.setTextColor(rgb(40, 180, 99))
        }else if (list[position].status == "Dead"){
            holder.status.text = list[position].status
            holder.status.setTextColor(rgb( 192, 57, 43 ))
        }else{
            holder.status.text = list[position].status
            holder.status.setTextColor(rgb(  144, 148, 151 ))
        }
        Picasso.get()
            .load(list[position].image)
            .into(holder.image)

        holder.itemView.setOnClickListener {
            var intent = Intent(holder.itemView.context,CharacterActivity::class.java)
            intent.putExtra("image",list[position].image)
            intent.putExtra("name",list[position].name)
            intent.putExtra("status",list[position].status)
            intent.putExtra("species",list[position].species)
            intent.putExtra("gender",list[position].gender)
            intent.putExtra("origin",list[position].origin.name)
            intent.putExtra("location",list[position].location.name)


            holder.itemView.context.startActivity(intent)
        }
    }





}

class CharacterHolder(view: View) : RecyclerView.ViewHolder(view) {
    var name = itemView.findViewById<TextView>(R.id.nameTV)
    var species = itemView.findViewById<TextView>(R.id.speciesTV)
    var status = itemView.findViewById<TextView>(R.id.statusTV)
    var image = itemView.findViewById<ImageView>(R.id.imageView)



}