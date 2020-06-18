package com.example.s1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.register.*

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)
        val phone: String = intent.getStringExtra("phone")

        register_personal.setOnClickListener {
            val i = Intent(this, fourth::class.java)
            i.putExtra("phone", phone)
            startActivity(i)

        }
        register_professional.setOnClickListener {
            val i = Intent(this, five::class.java)
            i.putExtra("phone", phone)
            startActivity(i)

        }
        register_payment.setOnClickListener {
            val i = Intent(this, six::class.java)
            i.putExtra("phone", phone)
            startActivity(i)

        }
        register_documents.setOnClickListener {
            val i = Intent(this, seven::class.java)
            i.putExtra("phone", phone)
            startActivity(i)

        }
        register_submit.setOnClickListener {
            val i = Intent(this, eight::class.java)
            i.putExtra("phone", phone)
            startActivity(i)

        }
        register_back.setOnClickListener {
            val i = Intent(this, third::class.java)
            i.putExtra("phone", phone)
            startActivity(i)
        }
    }
}