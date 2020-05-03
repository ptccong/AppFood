package com.example.apptest1kt.UI.SpashScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.apptest1kt.R
import com.example.apptest1kt.UI.LoginScreeen.LoginScreen

class MainActivity : AppCompatActivity() {
    var handler: Handler= Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handler = Handler()
        handler.postDelayed({
            startActivity(Intent(this,
                LoginScreen::class.java))
        },2000)

    }
}
