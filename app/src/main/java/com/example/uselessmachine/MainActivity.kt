package com.example.uselessmachine

import android.app.PendingIntent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_main_lookBusy.setOnClickListener {
            Toast.makeText(this, "Bottom Text: ${(it as Button).text.toString()}", Toast.LENGTH_SHORT).show()
            progressBar_main_lookBusy.visibility = View.VISIBLE
            button_main_selfDestruct.visibility = View.INVISIBLE
            button_main_lookBusy.visibility = View.INVISIBLE
            switch_main_useless.visibility = View.INVISIBLE
            textView_main_progress.visibility = View.VISIBLE
            textView_main_progress.text = "Uploading Numbers"
            var progressCheck = 0
            val uncheckTimer = object : CountDownTimer(10000, 500){
                override fun onFinish() {
                    progressBar_main_lookBusy.visibility = View.INVISIBLE
                    button_main_selfDestruct.visibility = View.VISIBLE
                    button_main_lookBusy.visibility = View.VISIBLE
                    switch_main_useless.visibility = View.VISIBLE
                    textView_main_progress.visibility = View.INVISIBLE
                }
                override fun onTick(milliseconds: Long) {
                    progressCheck += 5
                    progressBar_main_lookBusy.progress = progressCheck
                    textView_main_progress.text = "Generating Liquids: ${(Math.random() * 999999 + 1).toInt()} / 1000000"
                }
            }.start()
        }

        switch_main_useless.setOnCheckedChangeListener { compoundButton, isChecked ->
            var time = (Math.random() *  2000 + 3000).toInt()
            val uncheckTimer = object : CountDownTimer(time.toLong(), 1){
                override fun onFinish() {
                    if (switch_main_useless.isChecked){
                        switch_main_useless.isChecked = false
                    }
                }
                override fun onTick(milliseconds: Long) {
                    if (!switch_main_useless.isChecked){
                        cancel()
                    }
                }
            }
            if (isChecked) {
                Toast.makeText(this, "Don't touch me! I'll sue!" , Toast.LENGTH_SHORT).show()
                uncheckTimer.start()
            }
            else
            {
                uncheckTimer.cancel()
                Toast.makeText(this, "Muda! Muda!" , Toast.LENGTH_SHORT).show()
            }

        }

        button_main_selfDestruct.setOnClickListener {
            val redButton = object : CountDownTimer(10000, 200) {
                override fun onFinish() {
                    finish()
                }
                var everyOtherCheck = 0
                var colorChoose = true
                override fun onTick(milliseconds: Long) {

                    fun setWhite(){
                        layout_main.setBackgroundColor(Color.rgb(255, 255, 255))
                        colorChoose = true
                    }

                    fun setRed(){
                        layout_main.setBackgroundColor(Color.rgb(255, 0, 0))
                        colorChoose = false
                    }

                    fun setColor(){
                        if (colorChoose){
                            setRed()
                        }
                        else{
                            setWhite()
                        }
                    }

                    button_main_selfDestruct.text = "Time Left to Vote: ${(milliseconds / 1000)}"
                    if (milliseconds < 3500){
                        setColor()
                    }
                    if (everyOtherCheck == 2) {
                        everyOtherCheck = 0
                        setColor()
                    }
                    else {
                        everyOtherCheck++
                    }
                }
            }.start()
            Toast.makeText(this, "EMERGENCY MEETING" , Toast.LENGTH_SHORT).show()
            button_main_selfDestruct.isEnabled = false

        }
    }
}