package com.example.harrypotterquizapp.reciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.harrypotterquizapp.repository.NetworkStatusRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NetworkReceiver : BroadcastReceiver() {

    var netWorkStatusRepository: NetworkStatusRepository? = null
        @Inject set

    override fun onReceive(context: Context?, intent: Intent?) {
        netWorkStatusRepository?.updateNetworkStatus(context?.isRestricted?: false)
    }

}

