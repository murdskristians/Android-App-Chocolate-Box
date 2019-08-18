package com.testdevlab.tdl_chocolateboxapp.utils

import android.content.Context
import com.google.gson.Gson
import com.testdevlab.tdl_chocolateboxapp.models.Chocolate
import com.testdevlab.tdl_chocolateboxapp.models.ChocolateWrapper

class CacheHandler (context: Context){

    private val preferences = context.getSharedPreferences("preferences", 0 )
    private val id = "chocolate_list"

    fun saveChocolates (chocolates: ArrayList<Chocolate>) {
        val wrapper = ChocolateWrapper(chocolates)
        preferences.edit().putString(id, Gson().toJson(wrapper)).apply()
    }

    fun getChocolates(): ArrayList<Chocolate>? {
        val string = preferences.getString(id , null)

        if (string != null) {
            val wrapper = Gson().fromJson(string, ChocolateWrapper::class.java)
            return wrapper.chocolates
        }

        return null
    }
}