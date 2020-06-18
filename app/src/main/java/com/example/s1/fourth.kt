package com.example.s1

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import kotlinx.android.synthetic.main.page_4.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


class fourth : AppCompatActivity() {
    private var c: Int = 0
    private lateinit var gender: String
    private lateinit var husbandorfather: String
    //private val BASE_URL = "http://192.168.43.114:3000"
    private val BASE_URL = "https://stet2020.herokuapp.com/"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_4)
        page_4_progressBar.progress = 20
        page_4_enter_phn_no_1.text = intent.getStringExtra("phone")

        page_4_back.setOnClickListener {
            val i = Intent(this, Register::class.java)
            i.putExtra("phone", page_4_enter_phn_no_1.text.toString())
            startActivity(i)
        }

        page_4_calender.setOnClickListener {
            val datePickerDialog: DatePickerDialog


            val mYear = 1999

            val mMonth = 5

            val mDay = 8
            datePickerDialog = DatePickerDialog(
                this,
                OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    page_4_enter_dob.setText(
                        dayOfMonth.toString() + "/"
                                + (monthOfYear + 1) + "/" + year
                    )

                }, mYear, mMonth, mDay
            )
            datePickerDialog.show()

        }
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var retrofitInterface: RetrofitInterface = retrofit.create(RetrofitInterface::class.java)

        page_4_next.setOnClickListener {

            c += validName(page_4_enter_fname)
            +validName(page_4_enter_lname)
            +validName(page_4_enter_father_fname)
            +validName(page_4_enter_father_lname)
            +validName(page_4_enter_mother_fname)
            +validName(page_4_enter_mother_lname)
            +validHusbandorFather(page_4_father, page_4_husband)
            +validNumber(page_4_enter_phn_no_2, 10)
            +validNumber(page_4_enter_postal_zip_1, 6)
            +validNumber(page_4_enter_aadhar_no, 16)
            +validEmail(page_4_enter_email_2)
            +validDOB(page_4_enter_dob)
            +all(
                page_4_enter_postal_address_2,
                page_4_enter_postal_address_district_2,
                page_4_enter_postal_zip_2,
                page_4_state_2,
                page_4_spin_states_2
            )
            +validSpinner(page_4_spin_community)
            +validSpinner(page_4_spin_states_1)
            +validAddress(page_4_enter_postal_address_1)
            +validAddress(page_4_enter_postal_address_district_1)
            +validGender(page_4_male, page_4_female, page_4_other)
            val call1: Call<LoginResult?>? =
                retrofitInterface.getDetails(page_4_enter_phn_no_1.text.toString())
            call1!!.enqueue(object : Callback<LoginResult?> {
                override fun onResponse(
                    call1: Call<LoginResult?>,
                    response: Response<LoginResult?>
                ) {
                    var result = response.body()

                }

                override fun onFailure(
                    call1: Call<LoginResult?>,
                    t: Throwable
                ) {
                    call1.cancel()
                }
            })
            if (c == 0) {
                val i = Intent(this, five::class.java)
                var state2: String = page_4_spin_states_2.selectedItem.toString()
                if (state2 == "Select") {
                    state2 = ""
                }
                val Personal: HashMap<String, String> = HashMap()
                Personal["Fname"] = page_4_enter_fname.text.toString()
                Personal["Mname"] = page_4_enter_mname.text.toString()
                Personal["Lname"] = page_4_enter_mname.text.toString()
                Personal["gender"] = gender
                Personal["FH"] = husbandorfather
                Personal["FHFname"] = page_4_enter_father_fname.text.toString()
                Personal["FHMname"] = page_4_enter_father_mname.text.toString()
                Personal["FHLname"] = page_4_enter_father_lname.text.toString()
                Personal["MFname"] = page_4_enter_mother_fname.text.toString()
                Personal["MMname"] = page_4_enter_mother_mname.text.toString()
                Personal["MLname"] = page_4_enter_mother_lname.text.toString()
                Personal["DOB"] = page_4_enter_dob.text.toString()
                Personal["Category"] = page_4_spin_community.selectedItem.toString()
                Personal["Aadhar"] = page_4_enter_aadhar_no.text.toString()
                Personal["AddressOne"] = page_4_enter_postal_address_1.text.toString()
                Personal["DistrictOne"] = page_4_enter_postal_address_district_1.text.toString()
                Personal["StateOne"] = page_4_spin_states_1.selectedItem.toString()
                Personal["PinCodeOne"] = page_4_enter_postal_zip_1.text.toString()
                Personal["AddressTwo"] = page_4_enter_postal_address_2.text.toString()
                Personal["DistrictTwo"] = page_4_enter_postal_address_district_2.text.toString()
                Personal["StateTwo"] = state2
                Personal["PinCodeTwo"] = page_4_enter_postal_zip_2.text.toString()
                Personal["Phone1"] = page_4_enter_phn_no_1.text.toString()
                Personal["Phone2"] = page_4_enter_phn_no_2.text.toString()
                Personal["Email1"] = page_4_enter_email_1.text.toString()
                Personal["Email2"] = page_4_enter_email_2.text.toString()
                val call: Call<Void?>? = retrofitInterface.executeDetail(Personal)
                call!!.enqueue(object : Callback<Void?> {
                    override fun onResponse(
                        call: Call<Void?>?,
                        response: Response<Void?>
                    ) {
                        if (response.code() == 200) {
                            Log.d("Success", "Data Stored")
                            Toast.makeText(
                                this@fourth,
                                "Data stored successfully", Toast.LENGTH_LONG
                            ).show()
                            val it = Intent(this@fourth, five::class.java)
                            startActivity(it)
                        } else {
                            Log.d("Success", "Data already Stored")
                            Toast.makeText(
                                this@fourth,
                                "Aadhar already registered", Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                    override fun onFailure(
                        call: Call<Void?>?,
                        t: Throwable
                    ) {
                        Log.d("Failure", t.message)
                        Toast.makeText(
                            this@fourth, t.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })



                startActivity(i)
            } else {
                Toast.makeText(this, "Please check errors", Toast.LENGTH_LONG).show()
                c = 0
            }


        }
    }


    private fun validName(editText: EditText): Int {
        var x = 0
        editText.text.toString().validator()
            .nonEmpty()
            .atleastOneUpperCase()
            .noNumbers()
            .noSpecialCharacters()
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

    private fun validDOB(editText: EditText): Int {
        var x = 0
        editText.text.toString().validator()
            .nonEmpty()
            .addErrorCallback {
                editText.error = "Enter field in DOB Format"
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
            Toast.makeText(this, "Select At least One", Toast.LENGTH_SHORT).show()
        }

        return x
    }

    private fun validAddress(editText: EditText): Int {
        var x = 0
        editText.text.toString().validator()
            .nonEmpty()
            .noSpecialCharacters()
            .addErrorCallback {
                editText.error = "Enter valid address format"
                x = 1
            }
            .addSuccessCallback {
                x = 0

            }
            .check()
        return x
    }

    private fun validGender(
        radioButton1: RadioButton,
        radioButton2: RadioButton,
        radioButton3: RadioButton
    ): Int {
        var x: Int
        if (radioButton1.isChecked || radioButton2.isChecked || radioButton3.isChecked) {
            x = 0
            gender = if (radioButton1.isChecked) {
                "Male"
            } else if (radioButton2.isChecked) {
                "Female"
            } else {
                "Others"
            }
        } else {
            Toast.makeText(this, "Please Select Gender", Toast.LENGTH_LONG).show()
            x = 1
        }
        return x
    }

    private fun validHusbandorFather(
        radioButton1: RadioButton,
        radioButton2: RadioButton
    ): Int {
        var x: Int
        if (radioButton1.isChecked || radioButton2.isChecked) {
            x = 0
            husbandorfather = if (radioButton1.isChecked) {
                "Father"
            } else {
                "Husband"
            }
        } else {
            Toast.makeText(this, "Please Select one", Toast.LENGTH_LONG).show()
            x = 1
        }
        return x
    }

    private fun field2(edit1: EditText): Int {
        return if (edit1.text.toString() == "" || validAddress(edit1) == 0) {
            0
        } else {
            1
        }
    }

    private fun field3(edit1: EditText): Int {
        return if (edit1.text.toString() == "" || validNumber(edit1, 6) == 0) {
            0
        } else {
            1
        }
    }

    private fun all(
        edt1: EditText,
        edt2: EditText,
        edt3: EditText,
        txt: TextView,
        spin1: Spinner
    ): Int {
        return if (field2(edt1) == 0 && field2(edt2) == 0 && field3(edt3) == 0 && validSpinner(spin1) == 0) {
            0
        } else {
            1
        }
    }

}
