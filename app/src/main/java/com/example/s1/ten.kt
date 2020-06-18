package com.example.s1

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import kotlinx.android.synthetic.main.page_1_signup.*

class ten : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_1_signup)
        page_1_signup_back.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

        page_1_signup_verify.setOnClickListener {

            var c: Int = validName(page_1_signup_name_et) + validEmail(page_1_signup_email_et)
            +validNumber(page_1_signup_phn_et, 10) + validNumber(
                page_1_signup_Edtaadhar,
                16
            ) + isValidPassword(page_1_signup_Edtpass.text.toString())
            if (c == 0) {
                val i = Intent(this, second::class.java)
                i.putExtra("Name", page_1_signup_name_et.text.toString())
                i.putExtra("Email", page_1_signup_email_et.text.toString())
                i.putExtra("Phone", page_1_signup_phn_et.text.toString())
                i.putExtra("Aadhar", page_1_signup_Edtaadhar.text.toString())
                i.putExtra("Password", page_1_signup_Edtpass.text.toString())
                startActivity(i)
            } else {

            }
        }
    }

    private fun validName(editText: EditText): Int {
        var x = 0
        editText.text.toString().validator()
            .nonEmpty()
            .atleastOneUpperCase()
            .noNumbers()
            .addErrorCallback {
                editText.error =
                    "Enter only Alphabets\n No Numbers and special character are allowed\n First letter must be Capital"
                x = 1
            }
            .addSuccessCallback {
                x = 0

            }
            .check()
        return x
    }

    private fun validNumber(editText: EditText, d: Int): Int {
        var x = 0
        editText.text.toString().validator()
            .nonEmpty()
            .validNumber()
            .onlyNumbers()
            .maxLength(d)
            .minLength(d)
            .addErrorCallback {
                editText.error = "Enter $d digit Number Only"
                x = 1
            }
            .addSuccessCallback {
                x = 0

            }
            .check()
        return x
    }

    private fun validEmail(editText: EditText): Int {
        var x = 0
        editText.text.toString().validator()
            .validEmail()
            .nonEmpty()
            .addErrorCallback {
                editText.error = "Enter email in correct format eg. xyz@gmail.com"
                x = 1
            }
            .addSuccessCallback {
                x = 0

            }
            .check()
        return x
    }

    private fun isValidPassword(password: String?): Int {
        return if (password != null) {
            if (password.length < 5) {
                Toast.makeText(this@ten, "Password must be more than 5 char", Toast.LENGTH_SHORT)
                    .show()
                1
            } else {
                0
            }
        } else {
            Toast.makeText(this@ten, "Password must be more than 5 char", Toast.LENGTH_SHORT).show()
            1
        }
    }
}
