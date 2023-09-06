package com.example.harrypotterquizapp

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.harrypotterquizapp.ui.signin.SignInFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, SignInFragment())
            .commit()
    }

    private var mediaPlayer: MediaPlayer? = null?.let {
        it {

            mediaPlayer = MediaPlayer.create(this, R.raw.harry_potter_soundtrack)
            mediaPlayer?.isLooping = true
            mediaPlayer?.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.stop()
        mediaPlayer?.release()
    }

}