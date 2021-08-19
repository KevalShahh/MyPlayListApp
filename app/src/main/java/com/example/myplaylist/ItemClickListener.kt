package com.example.myplaylist

import android.media.MediaPlayer

interface ItemClickListener {
    fun onItemClick(name:String,artists:String,path:String,stime:Int,etime:Int,player:MediaPlayer)
}