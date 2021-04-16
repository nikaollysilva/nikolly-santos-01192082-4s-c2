package com.example.appcachorros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun irParaSumario(view: View) {
        val telaSumario = Intent(this, SumarioCachorros::class.java)
        startActivity(telaSumario)
    }

    fun irParaCadastroCachorro(view: View) {
        val telaCadastroCachorro = Intent(this, CadastroCachorro::class.java)
        startActivity(telaCadastroCachorro)
    }

}