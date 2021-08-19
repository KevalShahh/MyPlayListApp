package com.example.myplaylist

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.util.concurrent.TimeUnit


class MainActivity2 : AppCompatActivity() {
/*
    private var b1: ImageButton? = null
    private var b2: ImageButton? = null
    private var b3: ImageButton? = null
    private var b4: ImageButton? = null
    private var iv: ImageView? = null
    private var mediaPlayer: MediaPlayer? = null
    private var startTime = 0.0
    private val finalTime = 0.0
    private val myHandler: Handler = Handler()
    private val forwardTime = 5000
    private val backwardTime = 5000
    private var seekbar: SeekBar? = null
    private var tx1: TextView? = null
    private var tx2: TextView? = null
    private var tx3: TextView? = null
    private var oneTimeOnly = 0
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)}
/*
        b1 = findViewById(R.id.button)
        b2 = findViewById(R.id.button2)
        b3 = findViewById(R.id.button3)
        b4 = findViewById(R.id.button4)
        iv = findViewById(R.id.imageView)
        tx1 = findViewById(R.id.textView2)
        tx2 = findViewById(R.id.textView3)
        tx3 = findViewById(R.id.textView4)
        tx3!!.text = intent.getStringExtra("Songname")
        mediaPlayer=MediaPlayer()
        if (mediaPlayer!!.isPlaying) {
            seekbar = findViewById(R.id.seekBar)
            seekbar!!.isClickable = false

            val finalTime = mediaPlayer!!.duration
            var startTime = mediaPlayer!!.currentPosition
            if (oneTimeOnly == 0) {
                seekbar!!.max = finalTime
                oneTimeOnly = 1
            }
            tx2!!.text = String.format(
                "%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes(finalTime.toLong()),
                TimeUnit.MILLISECONDS.toSeconds(finalTime.toLong()) -
                        TimeUnit.MINUTES.toSeconds(
                            TimeUnit.MILLISECONDS.toMinutes(
                                finalTime.toLong()
                            )
                        )
            )

            tx1!!.text = String.format(
                "%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes(startTime.toLong()),
                TimeUnit.MILLISECONDS.toSeconds(startTime.toLong()) -
                        TimeUnit.MINUTES.toSeconds(
                            TimeUnit.MILLISECONDS.toMinutes(
                                startTime.toLong()
                            )
                        )
            )
            seekbar!!.progress = startTime
            myHandler.postDelayed(UpdateSongTime, 100)
            Toast.makeText(applicationContext, "Playing sound", Toast.LENGTH_SHORT).show()
            b3!!.isEnabled = false
            b2!!.isEnabled = true
            b2!!.setOnClickListener {
                Toast.makeText(applicationContext, "Pausing sound", Toast.LENGTH_SHORT).show()
                mediaPlayer!!.pause()
                b2!!.isEnabled = false
                b3!!.isEnabled = true
            }

            b1!!.setOnClickListener {
                val temp = startTime
                if ((temp + forwardTime) < finalTime) {
                    startTime += forwardTime
                    mediaPlayer?.seekTo(startTime.toInt())
                    Toast.makeText(
                        applicationContext,
                        "You have Jumped forward 5 seconds",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Cannot jump forward 5 seconds",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            b4!!.setOnClickListener {
                val temp = startTime
                if ((temp - backwardTime) > 0) {
                    startTime -= backwardTime
                    mediaPlayer?.seekTo(startTime.toInt())
                    Toast.makeText(
                        applicationContext,
                        "You have Jumped backward 5 seconds",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Cannot jump backward 5 seconds",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        else{
            Toast.makeText(this, "player not playing", Toast.LENGTH_SHORT).show()
        }


        */
/* mediaPlayer = MediaPlayer()
         val path = intent.getStringExtra("SongPath")!!
         val file = File(path)
         if (file.exists()) {
             mediaPlayer!!.setDataSource(file.path)
             mediaPlayer!!.prepare()
         }
         seekbar = findViewById(R.id.seekBar)
         seekbar!!.isClickable = false
         b2!!.isEnabled=false
         b3!!.setOnClickListener {
             Toast.makeText(applicationContext, "Playing sound", Toast.LENGTH_SHORT).show()
             mediaPlayer?.start()

             val finalTime = mediaPlayer?.duration
             val startTime = mediaPlayer?.currentPosition

             if (oneTimeOnly == 0) {
                 if (finalTime != null) {
                     seekbar!!.max = finalTime
                 }
                 oneTimeOnly = 1
             }

             if (finalTime != null) {
                 tx2!!.text = String.format(
                     "%d min, %d sec",
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
                 tx1!!.text = String.format(
                     "%d min, %d sec",
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
                 seekbar!!.progress = startTime
             }
             myHandler.postDelayed(UpdateSongTime, 100)
             b2!!.isEnabled = true
             b3!!.isEnabled = false
         }

         b2!!.setOnClickListener {
             Toast.makeText(applicationContext, "Pausing sound", Toast.LENGTH_SHORT).show()
             mediaPlayer?.pause()
             b2!!.isEnabled = false
             b3!!.isEnabled = true
         }

         b1!!.setOnClickListener {
             val temp = startTime
             if ((temp + forwardTime) < finalTime) {
                 startTime += forwardTime
                 mediaPlayer?.seekTo(startTime.toInt())
                 Toast.makeText(
                     applicationContext,
                     "You have Jumped forward 5 seconds",
                     Toast.LENGTH_SHORT
                 ).show()
             } else {
                 Toast.makeText(
                     applicationContext,
                     "Cannot jump forward 5 seconds",
                     Toast.LENGTH_SHORT
                 ).show()
             }
         }

         b4!!.setOnClickListener {
             val temp = startTime
             if ((temp - backwardTime) > 0) {
                 startTime -= backwardTime
                 mediaPlayer?.seekTo(startTime.toInt())
                 Toast.makeText(
                     applicationContext,
                     "You have Jumped backward 5 seconds",
                     Toast.LENGTH_SHORT
                 ).show()
             } else {
                 Toast.makeText(
                     applicationContext,
                     "Cannot jump backward 5 seconds",
                     Toast.LENGTH_SHORT
                 ).show()
             }
         }*//*

    }

    private val UpdateSongTime: Runnable = object : Runnable {
        override fun run() {
            startTime = mediaPlayer!!.currentPosition.toDouble()
            tx1!!.text = String.format(
                "%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes(startTime.toLong()),
                TimeUnit.MILLISECONDS.toSeconds(startTime.toLong()) -
                        TimeUnit.MINUTES.toSeconds(
                            TimeUnit.MILLISECONDS.toMinutes(
                                startTime.toLong()
                            )
                        )
            )
            seekbar!!.progress = startTime.toInt()
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
        mediaPlayer = player
        if (mediaPlayer!!.isPlaying) {
            seekbar = findViewById(R.id.seekBar)
            seekbar!!.isClickable = false

            val finalTime = mediaPlayer!!.duration
            var startTime = mediaPlayer!!.currentPosition
            if (oneTimeOnly == 0) {
                seekbar!!.max = finalTime
                oneTimeOnly = 1
            }
            tx2!!.text = String.format(
                "%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes(finalTime.toLong()),
                TimeUnit.MILLISECONDS.toSeconds(finalTime.toLong()) -
                        TimeUnit.MINUTES.toSeconds(
                            TimeUnit.MILLISECONDS.toMinutes(
                                finalTime.toLong()
                            )
                        )
            )

            tx1!!.text = String.format(
                "%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes(startTime.toLong()),
                TimeUnit.MILLISECONDS.toSeconds(startTime.toLong()) -
                        TimeUnit.MINUTES.toSeconds(
                            TimeUnit.MILLISECONDS.toMinutes(
                                startTime.toLong()
                            )
                        )
            )
            seekbar!!.progress = startTime
            myHandler.postDelayed(UpdateSongTime, 100)
            Toast.makeText(applicationContext, "Playing sound", Toast.LENGTH_SHORT).show()
            b3!!.isEnabled = false
            b2!!.isEnabled = true
            b2!!.setOnClickListener {
                Toast.makeText(applicationContext, "Pausing sound", Toast.LENGTH_SHORT).show()
                mediaPlayer!!.pause()
                b2!!.isEnabled = false
                b3!!.isEnabled = true
            }

            b1!!.setOnClickListener {
                val temp = startTime
                if ((temp + forwardTime) < finalTime) {
                    startTime += forwardTime
                    mediaPlayer?.seekTo(startTime.toInt())
                    Toast.makeText(
                        applicationContext,
                        "You have Jumped forward 5 seconds",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Cannot jump forward 5 seconds",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            b4!!.setOnClickListener {
                val temp = startTime
                if ((temp - backwardTime) > 0) {
                    startTime -= backwardTime
                    mediaPlayer?.seekTo(startTime.toInt())
                    Toast.makeText(
                        applicationContext,
                        "You have Jumped backward 5 seconds",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Cannot jump backward 5 seconds",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        else{
            Toast.makeText(this, "player not playing", Toast.LENGTH_SHORT).show()
        }
    }
*/

}