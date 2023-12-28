package com.tessuitmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class FirstScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_screen)

        val inputName = findViewById<EditText>(R.id.edt_name)
        val inputSentence = findViewById<EditText>(R.id.edt_palindrome)
        val checkButton = findViewById<Button>(R.id.btn_check)

        checkButton.setOnClickListener {
            val palindrome = inputSentence.text.toString().replace("\\s".toRegex(), "")
            val reversedPalindrome = palindrome.reversed()

            val isSentence = palindrome.equals(reversedPalindrome, ignoreCase = true)

            val message = if (isSentence){
                "isPalindrome"
            }else{
                "not palindrome"
            }

            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("Palindrome Check Result")
            alertDialogBuilder.setMessage(message)
            alertDialogBuilder.setPositiveButton("OK"){ dialog, _ ->
                dialog.dismiss()
            }

            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }
    }
}