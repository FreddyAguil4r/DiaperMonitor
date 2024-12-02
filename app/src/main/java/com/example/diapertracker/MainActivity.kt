package com.example.diapertracker

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    //Declare var's but set the value later
    private lateinit var dirtyButton : RadioButton
    private lateinit var wetButton: RadioButton
    private lateinit var dryButton: RadioButton
    private lateinit var currentTime : EditText
    private lateinit var diaperChangesText : TextView
    private lateinit var diaperChangesCount : TextView

    //Counter variable
    private var diaperCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Get a reference buttons and set listeners
        val addButton : Button = findViewById(R.id.main_activity_bt_add)
        val clearButton : Button = findViewById(R.id.main_activity_bt_clear)

        addButton.setOnClickListener { addNewDiaper() }
        clearButton.setOnClickListener { clear() }

        //Sets values to other views
        dirtyButton = findViewById(R.id.main_activity_rb_dirty)
        wetButton = findViewById(R.id.main_activity_rb_wet)
        dryButton = findViewById(R.id.main_activity_rb_dry)
        currentTime = findViewById(R.id.main_activity_et_time)
        diaperChangesText = findViewById(R.id.main_activity_tv_diaper_changes)
        diaperChangesCount = findViewById(R.id.main_activity_tv_diaper_count)
    }

    //Create a diaper to add a List
    private fun addNewDiaper() {
        //Get the current time
        var timeOfChange = "00:00"
        if(currentTime.text.toString() != "") {
            timeOfChange = currentTime.text.toString()
        }

        var newDiaper = ""
        newDiaper = if(dirtyButton.isChecked){
            "- A dirty diaper was changed at $timeOfChange"
        }else if (wetButton.isChecked){
            "- A wet diaper was changed at $timeOfChange"
        }else{
            "- A dry diaper was changed at $timeOfChange"
        }
        diaperCount++

        //Update our diaper list
        updateDiaperList(newDiaper)
    }

    private fun updateDiaperList(newDiaper : String) {
        val oldDiapers = diaperChangesText.text.toString()
        val updatedDiapers = "$oldDiapers \n$newDiaper"

        diaperChangesText.text = updatedDiapers
        diaperChangesCount.text = "$diaperCount total diapers changed"

        //Clean the editText
        currentTime.setText("")

        //Hide the keyboard
        hideKeyboard()
    }

    private fun hideKeyboard() {
        val imn = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imn.hideSoftInputFromWindow(currentTime.windowToken,0)
    }

    private fun clear() {
        //Reset all UI Text and counters
        diaperChangesText.text = ""
        diaperChangesCount.text = ""
        diaperCount = 0
    }
}