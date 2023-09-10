package com.example.harrypotterquizapp.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.harrypotterquizapp.model.Answer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class AnswerTypeConverter {

    @TypeConverter
    fun listAnswer(list: ArrayList<Answer>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun stringToList(json: String): ArrayList<Answer> {
        val listType = object : TypeToken<ArrayList<Answer>>() {}.type
        return Gson().fromJson(json, listType)
    }
}