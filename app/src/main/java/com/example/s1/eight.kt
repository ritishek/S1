package com.example.s1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.page_2.*
import kotlinx.android.synthetic.main.page_3.*
import kotlinx.android.synthetic.main.page_8.*

class eight : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_8)
        page_8_progress_bar.progress = 100
        page_8_submit.setOnClickListener {
            Toast.makeText(this, "Registration Successfull", Toast.LENGTH_SHORT).show()
        }

    }
}
