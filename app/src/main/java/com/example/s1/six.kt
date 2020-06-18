package com.example.s1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.page_6.*

class six : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_6)
        val phone: String = intent.getStringExtra("phone")
        page_6_progress_bar.progress = 60

        page_6_next.setOnClickListener {

            val i = Intent(this, Register::class.java)
            i.putExtra("phone", phone)
            startActivity(i)

        }
        page_6_back.setOnClickListener {
            val i = Intent(this, Register::class.java)
            i.putExtra("phone", phone)
            startActivity(i)


        }

    }
}

