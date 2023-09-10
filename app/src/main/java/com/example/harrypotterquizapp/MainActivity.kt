package com.example.harrypotterquizapp

import android.content.Context
import android.content.IntentFilter
import android.media.MediaPlayer
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.harrypotterquizapp.databinding.ActivityMainBinding
import com.example.harrypotterquizapp.repository.NetworkStatusRepository
import com.example.harrypotterquizapp.ui.signin.SignInFragment
import com.example.harrypotterquizapp.ui.signup.SignUpFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var networkStatusRepository: NetworkStatusRepository

    private lateinit var binding: ActivityMainBinding

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val filter = IntentFilter().apply {
            addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        }
        val receiver = null
        registerReceiver(receiver, filter)
        networkStatusRepository.updateNetworkStatus(this.getNetworkStatus())
        lifecycleScope.launch {
            networkStatusRepository.getNetworkState()
                .filter { !it }
                .collectLatest {
                    Toast.makeText(
                        this@MainActivity, "no internet connection", Toast.LENGTH_LONG
                    )
                        .show()
                }
        }

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        mediaPlayer = MediaPlayer.create(this, R.raw.harry_potter_soundtrack)
        mediaPlayer?.isLooping = true
        mediaPlayer?.start()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.container.id, SignInFragment())
                .commit()
        }


        binding.root.setOnClickListener {

            val signUpFragment = SignUpFragment()

            supportFragmentManager.beginTransaction()
                .replace(binding.container.id, signUpFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun getNetworkStatus(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val networkInfo = connectivityManager?.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.stop()
        mediaPlayer?.release()
    }
}