package com.example.myplaylist

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class CustomMusicAdapter(
    private var context: Context,
    private var arrayList: ArrayList<PlaylistModel>,
    private var clickListner: ItemClickListener
) :
    RecyclerView.Adapter<CustomMusicAdapter.MusicViewHolder>() {
    private var player: MediaPlayer = MediaPlayer()

    class MusicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textview1 = itemView.findViewById<TextView>(R.id.tv_song)!!
        var textvoew2 = itemView.findViewById<TextView>(R.id.tv_artist)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_music_list, parent, false)
        return MusicViewHolder(view)
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.textview1.text = arrayList[position].name
        holder.textvoew2.text = arrayList[position].artists
        holder.itemView.setOnClickListener {
            try {
                    val path = arrayList[position].path
                    val file = File(path)
                    if (file.exists()) {
                        player.setDataSource(file.path)
                        player.prepare()
                        player.start()
                        var stime = player.currentPosition
                        var etime = player.duration
                        Log.d("TAG", "onBindViewHolder: " + stime)
                        Log.d("TAG", "onBindViewHolder: " + etime)
                        Log.d("TAG", "onBindViewHolder: " + player.isPlaying)
                        clickListner.onItemClick(
                            arrayList[position].name,
                            arrayList[position].artists,
                            path,
                            stime,
                            etime,
                            player
                        )
                    }
            } catch (e: Exception) {
                Log.d("TAG", "onBindViewHolder: " + e.message)
            }

        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    fun filterList(filterllist: ArrayList<PlaylistModel>) {
        arrayList = filterllist
        notifyDataSetChanged()
    }
}
