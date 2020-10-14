package com.example.retrofit2tutorial.recyclerview

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit2tutorial.CharacterActivity
import com.example.retrofit2tutorial.EpisodeActivity
import com.example.retrofit2tutorial.Episodes.Episode
import com.example.retrofit2tutorial.R
import org.w3c.dom.Text
import java.util.zip.Inflater

class EpisodeRecyclerAdapter (private val list : ArrayList<Episode>) : RecyclerView.Adapter<EpisodeRecyclerAdapter.EpisodeHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.episode_item, parent, false)
        return EpisodeHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: EpisodeHolder, position: Int) {
        holder.title.text = list[position].name
        holder.episode.text = list[position].episode
        holder.air_date.text = list[position].air_date

        holder.itemView.setOnClickListener {
            var intent = Intent(holder.itemView.context, EpisodeActivity::class.java)
            intent.putExtra("episode_name",list[position].name)
            intent.putExtra("episode_airDate",list[position].air_date)
            intent.putExtra("episode_episode",list[position].episode)
            intent.putExtra("episode_created",list[position].created)
            intent.putExtra("episode_characters",list[position].characters )


            holder.itemView.context.startActivity(intent)
        }


    }

    class EpisodeHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title = view.findViewById<TextView>(R.id.episodeTitleTV)
        var episode = view.findViewById<TextView>(R.id.episodeTV)
        var air_date = view.findViewById<TextView>(R.id.air_dateTV)
    }
}