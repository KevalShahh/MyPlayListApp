package com.example.myplaylist

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myplaylist.databinding.ActivityMainBinding
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), ItemClickListener {
    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var arrayList: ArrayList<PlaylistModel>
    private lateinit var adapter: CustomMusicAdapter

    private var mediaPlayer: MediaPlayer? = null
    private var startTime = 0.0
    private val finalTime = 0.0
    private val myHandler: Handler = Handler()
    private val forwardTime = 5000
    private val backwardTime = 5000
    private var oneTimeOnly = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                1
            )
        } else {
            getData()
        }
        viewBinding.button.setOnClickListener {
            val temp = startTime
            if ((temp + forwardTime) < finalTime) {
                startTime += forwardTime
                mediaPlayer?.seekTo(startTime.toInt())

            } else {
                Toast.makeText(
                    applicationContext,
                    "Cannot jump forward 5 seconds",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        viewBinding.button2.setOnClickListener {
            mediaPlayer?.pause()
            viewBinding.button2.isEnabled = false
            viewBinding.button3.isEnabled = true
        }
        viewBinding.button3.setOnClickListener {
            mediaPlayer?.start()
            val finalTime = mediaPlayer?.duration
            val startTime = mediaPlayer?.currentPosition

            if (oneTimeOnly == 0) {
                if (finalTime != null) {
                    viewBinding.seekBar.max = finalTime
                }
                oneTimeOnly = 1
            }

            if (finalTime != null) {
                viewBinding.textView3.text = String.format(
                    "%d:%d",
                    TimeUnit.MILLISECONDS.toMinutes(finalTime.toLong()),
                    TimeUnit.MILLISECONDS.toSeconds(finalTime.toLong()) -
                            TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(
                                    finalTime.toLong()
                                )
                            )
                )
            }

            if (startTime != null) {
                viewBinding.textView2.text = String.format(
                    "%d:%d",
                    TimeUnit.MILLISECONDS.toMinutes(startTime.toLong()),
                    TimeUnit.MILLISECONDS.toSeconds(startTime.toLong()) -
                            TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(
                                    startTime.toLong()
                                )
                            )
                )
            }

            if (startTime != null) {
                viewBinding.seekBar.progress = startTime
            }
            myHandler.postDelayed(UpdateSongTime, 100)
            viewBinding.button2.isEnabled = true
            viewBinding.button3.isEnabled = false
        }
        viewBinding.button4.setOnClickListener {
            val temp = startTime
            if ((temp - backwardTime) > 0) {
                startTime -= backwardTime
                mediaPlayer?.seekTo(startTime.toInt())

            } else {
                Toast.makeText(
                    applicationContext,
                    "Cannot jump backward 5 seconds",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu?.findItem(R.id.item_search)
        val searchView: SearchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    filter(text = newText)
                }
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    fun filter(text: String) {
        val filteredlist: ArrayList<PlaylistModel> = ArrayList()
        for (item in arrayList) {
            if (item.getSong().lowercase(Locale.getDefault())
                    .contains(text.lowercase(Locale.getDefault()))
            ) {
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            adapter.filterList(filteredlist)
        }
    }

    @SuppressLint("Recycle")
    private fun getMusic() {
        arrayList = ArrayList()
        val contentResolver = contentResolver
        val songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val songCursor = contentResolver.query(songUri, null, null, null, null)
        if (songCursor != null && songCursor.moveToFirst()) {
            val songTitle = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE)
            val songArtists = songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)
            val songPath = songCursor.getColumnIndex(MediaStore.Audio.Media.DATA)
            do {
                val currentTitle = songCursor.getString(songTitle)
                val currentArtists = songCursor.getString(songArtists)
                val currentPath = songCursor.getString(songPath)
                Log.d("TAG", "getMusic: $currentPath")
                arrayList.add(PlaylistModel(currentTitle, currentArtists, currentPath))
            } while (songCursor.moveToNext())
        }
    }

    private fun getData() {
        getMusic()
        adapter = CustomMusicAdapter(this, arrayList, this)
        viewBinding.rvPlaylist.adapter = adapter
        viewBinding.rvPlaylist.layoutManager = LinearLayoutManager(this)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(
                            this,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        Toast.makeText(this, "permission granted", Toast.LENGTH_SHORT).show()
                        getData()
                    } else {
                        Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    return
                }
            }
        }
    }

    private val UpdateSongTime: Runnable = object : Runnable {
        override fun run() {
            startTime = mediaPlayer!!.currentPosition.toDouble()
            viewBinding.textView2.text = String.format(
                "%d:%d",
                TimeUnit.MILLISECONDS.toMinutes(startTime.toLong()),
                TimeUnit.MILLISECONDS.toSeconds(startTime.toLong()) -
                        TimeUnit.MINUTES.toSeconds(
                            TimeUnit.MILLISECONDS.toMinutes(
                                startTime.toLong()
                            )
                        )
            )
            viewBinding.seekBar.progress = startTime.toInt()
            myHandler.postDelayed(this, 100)
        }
    }

    override fun onItemClick(
        name: String,
        artists: String,
        path: String,
        stime: Int,
        etime: Int,
        player: MediaPlayer
    ) {
        viewBinding.tvSongCurrent.text = name
        viewBinding.tvArtistCurrent.text = artists
        viewBinding.cardview.visibility = View.VISIBLE

        mediaPlayer = player
        viewBinding.seekBar.isClickable = false
        viewBinding.button2.isEnabled = false
    }
}