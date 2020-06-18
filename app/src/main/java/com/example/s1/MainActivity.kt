package com.example.s1

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import kotlinx.android.synthetic.main.page_1.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {


    //private val BASE_URL = "http://192.168.43.114:3000"
    private val BASE_URL = "https://stet2020.herokuapp.com/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_1)
        setContentView(R.layout.page_1)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var retrofitInterface: RetrofitInterface = retrofit.create(RetrofitInterface::class.java)

        page_1_login.setOnClickListener {
            var c: Int = validNumber(page_1_phn_et, 10)
            if (c == 0) {
                val progress = ProgressDialog(this)
                progress.setMessage("Verifying Credentials :) ")
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER)
                progress.isIndeterminate = true
                progress.show()
                val map: HashMap<String?, String?> = HashMap()

                map["phone"] = page_1_phn_et.text.toString()
                map["password"] = page_1_Edtpass.text.toString()

                val call: Call<LoginResult?>? = retrofitInterface.executeLogin(map)

                call!!.enqueue(object : Callback<LoginResult?> {
                    override fun onResponse(
                        call: Call<LoginResult?>?,
                        response: Response<LoginResult?>
                    ) {
                        if (response.code() == 200) {
                            var result = response.body()

                            Toast.makeText(
                                this@MainActivity, "Login Successfully",
                                Toast.LENGTH_LONG
                            ).show()
                            progress.dismiss()
                            val i = Intent(this@MainActivity, third::class.java)
                            i.putExtra("phone", page_1_phn_et.text.toString())
                            startActivity(i)
                        } else if (response.code() == 404) {
                            Toast.makeText(
                                this@MainActivity, "Wrong Credentials",
                                Toast.LENGTH_LONG
                            ).show()
                            progress.dismiss()
                        }
                    }

                    override fun onFailure(
                        call: Call<LoginResult?>?,
                        t: Throwable
                    ) {
                        Toast.makeText(
                            this@MainActivity, t.message,
                            Toast.LENGTH_LONG
                        ).show()
                        progress.dismiss()
                    }

                })

            } else {
                Toast.makeText(this, "Check Error", Toast.LENGTH_LONG).show()
            }
        }
        page_1_signup.setOnClickListener {
            val i = Intent(this, ten::class.java)
            startActivity(i)
        }
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



}
