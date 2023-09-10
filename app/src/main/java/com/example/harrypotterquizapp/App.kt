package com.example.harrypotterquizapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.internal.managers.ApplicationComponentManager

@HiltAndroidApp
class App : Application() {

    companion object {
        val applicationComponent: ApplicationComponentManager? = null
    }

}