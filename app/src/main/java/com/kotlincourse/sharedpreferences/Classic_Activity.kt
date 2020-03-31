package com.kotlincourse.sharedpreferences

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kotlincourse.sharedpreferences.Persistence.PreferenceHelper
import kotlinx.android.synthetic.main.activity_main.*

class Classic_Activity : AppCompatActivity() {
    // 1 - get instance from our preferences helper
    private lateinit var pHelper: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    // 2- Get the instance
        pHelper = PreferenceHelper.newInstances(applicationContext)!! //we use applicationcontext here so we can use it in all the activities.

        //4 - set the functions to the buttons, create the listeners for this.
        buttonClearForm.setOnClickListener { clearFun(etClassicPerName, etClassicPerUserName, etClassicPerPassword) }
        buttonSave.setOnClickListener { saveData() }
        buttonLoad.setOnClickListener { getSavedContent() }
        buttonClear.setOnClickListener { clearData(PREF_NAME, PREF_USERNAME, PREF_PASSWORD) }


    }

    //3 - create una function for clear the form
    fun clearFun(vararg view: EditText) {     //3 - add variable arguments. variable arguments is for receiving multiple objects from same type.

        //4 clear the form
        for (view in view){
            view.setText("") //with this we're cleaning the form
        }
        Toast.makeText(this, "Form cleared", Toast.LENGTH_SHORT).show()
    }

    fun saveData(){
        // for save data I need to take whatever I write in the editText and Ill save it on the preference helper
        pHelper.setString(PREF_NAME, etClassicPerName.text.toString())
        pHelper.setString(PREF_USERNAME, etClassicPerUserName.text.toString())
        pHelper.setString(PREF_PASSWORD, etClassicPerPassword.text.toString())

        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show()
    }

    fun getSavedContent(){
        etClassicPerName.setText(pHelper.getString(PREF_NAME) ?: "")
        etClassicPerUserName.setText(pHelper.getString(PREF_USERNAME) ?: "")
        etClassicPerPassword.setText(pHelper.getString(PREF_PASSWORD) ?: "")

        Toast.makeText(this, "Data Retrieved", Toast.LENGTH_SHORT).show()

    }

    fun clearData(vararg keys : String){
        for (key in keys){
            pHelper.clearString(key)
        }

        Toast.makeText(this, "Data Deleted", Toast.LENGTH_SHORT).show()
    }

}
