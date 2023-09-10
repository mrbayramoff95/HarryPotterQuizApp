package com.example.harrypotterquizapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Answer(
    val title: String,
    val isCorrect: Boolean
) : Parcelable
