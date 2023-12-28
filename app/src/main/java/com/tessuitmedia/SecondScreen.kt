package com.tessuitmedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SecondScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_screen)

        val firstScreenNameTextView = findViewById<TextView>(R.id.first_screen_name)
        val selectedUserNameTextView = findViewById<TextView>(R.id.selected_user_name)
        val chooseUserButton = findViewById<Button>(R.id.btn_choose_a_user)

        //mengambil nama dari first screen menggunkan intent extras
        val nameFromFirstScreen = intent.getStringExtra("USER_NAME")
        //menampilkan nama pada text view
        if (nameFromFirstScreen != null){
            firstScreenNameTextView.text = "$nameFromFirstScreen"
        }else{
            firstScreenNameTextView.text = "No name received"
        }

        //btn choose a user to third screen
        chooseUserButton.setOnClickListener {
            val intent = Intent(this, ThirdScreen::class.java)
            startActivity(intent)
        }

    }
}