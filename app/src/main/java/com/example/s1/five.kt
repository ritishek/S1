package com.example.s1

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import kotlinx.android.synthetic.main.page_5.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class five : AppCompatActivity() {
    var c: Int = 0
    // private val BASE_URL = "http://192.168.43.114:3000"
    private val BASE_URL = "https://stet2020.herokuapp.com/"
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_5)
        page_5_progress_bar.progress = 40
        val phone: String = intent.getStringExtra("phone")

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var retrofitInterface: RetrofitInterface = retrofit.create(RetrofitInterface::class.java)

        page_5_next.setOnClickListener {


            c += validPercentage(page_5_enter_percentage, 4)
            +validUniversity(page_5_enter_university)
            +validSpinner(page_5_spin_category)
            +validSpinner(page_5_spin_language)
            +validSpinner(page_5_spin_min_qualification)
            +validSpinner(page_5_spin_prof_qualification)
            if (c == 0) {
                val current = LocalDateTime.now()

                val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss")
                val myFirstDocument: HashMap<String, String> = HashMap()
                val myFirstDocument2: HashMap<String, String> = HashMap()

                /*      Stitch.initializeDefaultAppClient(
                          resources.getString(R.string.my_app_id)

                      ) //initialize mongo stitch ny giving app key obtained from stitch

                      val stitchAppClient = Stitch.getDefaultAppClient()  //get stitch client
                      stitchAppClient.auth.loginWithCredential(AnonymousCredential())  //anonymous authentication
                          .addOnSuccessListener {
                              //message to show that stitch app is connected
                          }
                      val mongoClient = stitchAppClient.getServiceClient(
                          RemoteMongoClient.factory,
                          "mongodb-atlas"
                      ) //get mongo client

                      val myCollection = mongoClient.getDatabase("test") //create collection by giving
                          .getCollection("table")    //dbname and collection name
                      val myFirstDocument = Document() */


                myFirstDocument["Percentage"] = page_5_enter_percentage.text.toString()
                myFirstDocument["University"] = page_5_enter_university.text.toString()
                myFirstDocument["MinQualification"] =
                    page_5_spin_min_qualification.selectedItem.toString()
                myFirstDocument["ProfessionalQualification"] =
                    page_5_spin_prof_qualification.selectedItem.toString()
                myFirstDocument2["ApplicationCategory"] =
                    page_5_spin_category.selectedItem.toString()
                myFirstDocument2["PaperLanguage"] = page_5_spin_language.selectedItem.toString()

                /* myFirstDocument["Time"] = current.format(formatter)
                 myFirstDocument["user_id"] = stitchAppClient.auth.user!!.id    ///unique user id
                 myCollection.insertOne(myFirstDocument)   //insert one document
                     .addOnSuccessListener {
                         Log.d("STITCH", "One document inserted")   //function or msg on success
                     } */

                val call: Call<Void?>? = retrofitInterface.executeEducation(myFirstDocument)
                call!!.enqueue(object : Callback<Void?> {
                    override fun onResponse(
                        call: Call<Void?>?,
                        response: Response<Void?>
                    ) {
                        if (response.code() === 200) {
                            Log.d("Success", "Data Stored")
                            Toast.makeText(
                                this@five,
                                "Data stored successfully", Toast.LENGTH_LONG
                            ).show()

                        }
                    }

                    override fun onFailure(
                        call: Call<Void?>?,
                        t: Throwable
                    ) {
                        Log.d("Failure", t.message)
                        Toast.makeText(
                            this@five, t.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })
                val call2: Call<Void?>? = retrofitInterface.executePreference(myFirstDocument2)
                call2!!.enqueue(object : Callback<Void?> {
                    override fun onResponse(
                        call2: Call<Void?>?,
                        response: Response<Void?>
                    ) {
                        if (response.code() === 200) {
                            Log.d("Success", "Data Stored")
                            Toast.makeText(
                                this@five,
                                "Data stored successfully", Toast.LENGTH_LONG
                            ).show()
                            val i = Intent(this@five, Register::class.java)
                            i.putExtra("phone", phone)
                            startActivity(i)
                        }
                    }

                    override fun onFailure(
                        call: Call<Void?>?,
                        t: Throwable
                    ) {
                        Log.d("Failure", t.message)
                        Toast.makeText(
                            this@five, t.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })


                //val i = Intent(this, six::class.java)

                /*i.putExtra("Name", "$Fname $Mname $Lname")
                i.putExtra("FatherName",Faname)
                i.putExtra("MotherName",Moname)
                i.putExtra("Gender",gender)
                i.putExtra("Community",community)
                i.putExtra("DOB",DOB)
                i.putExtra("Aadhar",aadhar)
                i.putExtra("Email",emailid)
                i.putExtra("Phone",phoneno)
                i.putExtra("Zip",zip)
                i.putExtra("State",State)
                i.putExtra("Postal Address",postal_Address)
                i.putExtra("Paper Choice",page_5_spin_language.selectedItem.toString())
                i.putExtra("Minimum Qualification",page_5_spin_min_qualification.selectedItem.toString())
                i.putExtra("Professional Qualification",page_5_spin_prof_qualification.selectedItem.toString())
                i.putExtra("Percentage",page_5_enter_percentage.text.toString())
                i.putExtra("University",page_5_enter_university.text.toString())
*/

                //startActivity(i)
            } else {
                Toast.makeText(this, "Please check errors", Toast.LENGTH_LONG).show()
                c = 0
            }

        }
        page_5_back.setOnClickListener {
            val i = Intent(this, Register::class.java)
            i.putExtra("phone", phone)
            startActivity(i)


        }

    }

    private fun validUniversity(editText: EditText): Int {
        var x = 0
        editText.text.toString().validator()
            .nonEmpty()
            .noSpecialCharacters()
            .addErrorCallback {
                editText.error = "Enter valid University format"
                x = 1
            }
            .addSuccessCallback {
                x = 0
            }
            .check()
        return x
    }

    private fun validPercentage(editText: EditText, d: Int): Int {
        var x = 0
        editText.text.toString().validator()
            .nonEmpty()
            .validNumber()
            .maxLength(d + 1)
            .minLength(d + 1)
            .addErrorCallback {
                editText.error = "Enter upto $d digit Number Only"
                x = 1
            }
            .addSuccessCallback {
                x = 0

            }
            .check()
        return x
    }

    private fun validSpinner(Spinner1: Spinner): Int {
        var x = 0
        if (Spinner1.selectedItem.toString().trim() == "Select") {
            x = 1
            Toast.makeText(this, "Select Atleast one", Toast.LENGTH_LONG).show()
        }

        return x
    }
}
