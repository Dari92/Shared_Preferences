package com.kotlincourse.sharedpreferences.Persistence

import android.content.Context
import android.content.SharedPreferences
import com.kotlincourse.sharedpreferences.SHARED_PREF

class PreferenceHelper {
    // I need to create a singleton for use this particular class
    // I'll be setting some functions that will help me to retriver also to clear my shared preferences

        //1
    private lateinit var appPref : SharedPreferences // This extending from Android Content and this is an Interface
    private lateinit var editor: SharedPreferences.Editor       // Editor will be passing the information to that particular shared prefereces ans itll managin all transations betewn our code and the shared // preferences

   // 8 - Create some functions in order to start saving, getting and clearing our preferences, they're the preferences that we delcared in Const.kt

    //10
    // save button
    fun setString (key: String, value: String?){ //I need to pass a key and a value
        editor = appPref.edit() ?:
        editor.putString(key, value)
        editor.apply()
    }

    //load button
    fun getString (key : String): String? {
        return appPref.getString(key, null)

    }

    //clear button
    fun clearString (key: String) = setString(key, null) //

    // ---------------------------------------------------------------------------------for the integer vale: AGE

    fun setInt (key: String, value: Int){ //I need to pass a key and a value
        editor = appPref.edit() ?:
                editor.putInt(key, value)
        editor.apply()
    }

    //load button
    fun getInt (key: String): Int? {
        return appPref.getInt(key, 20)

    }

    //clear button
    fun clearInt (key: String) = setString(key, null) //

    // ---------------------------------------------------------------------------------for the integer vale: AGE

    // 9 - the functions below are just for get 1 parameter, a lot people do the same for each parameter they have
    //In the below functions, editor just support just a kind of values in this case just editor.putString. if we have for example: doubles or integer we need to do these functions for each kind of value
    // to prevent this, we work with the above functions.
   /* fun setName(name : String){
        editor = appPref?.edit() ?:
        editor.putString(PREF_NAME, name)
        editor.apply()
    }

    fun getName(): String? {
        return appPref?.getString(PREF_NAME, null)
    }*/



    //2 - Singleton
    companion object{

        //3 - Creating the new instance
        fun newInstances(context: Context): PreferenceHelper?{ // 4 - I need to work with the context bc sharedpreferences we need to work with the context that we're in that particular time.
            val phelper = PreferenceHelper() // 5 - new instance de PreferenceHelper
            phelper.appPref = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE) //6 - initialize the instance -- getSharedPreferences require 2 values 1:name Shared preferences is in the cons.kt, and the 2nd: a mode  Context.Mode private is the most common used in shared preferences
            // mode world readable is dangerous bc this means that any application it doest matter is is our application or other application can read our preferences.

            return phelper // 7 - return preference helper

            //I don' t initialize the editor bc it'll have an error, Ill initialize until I start working with the functions.

        }
    }
}