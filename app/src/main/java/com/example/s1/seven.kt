package com.example.s1

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.page_7.*
import java.io.IOException


class seven : AppCompatActivity() {
    private val PICK_IMAGE_REQUEST = 1
    private val CLICK_PHOTO = 2
    private var bit: Bitmap? = null
    private var filePath: Uri? = null
    var t: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_7)
        page_7_progress_bar.progress = 90
        val phone: String = intent.getStringExtra("phone")
        page_7_next.setOnClickListener {
            if (page_7_checkbox.isChecked) {

                val i = Intent(this, Register::class.java)
                i.putExtra("phone", phone)
                startActivity(i)
            } else {
                Toast.makeText(this, "Please Accept the T&C for Registration", Toast.LENGTH_SHORT)
                    .show()
            }

        }
        page_7_back.setOnClickListener {
            val i = Intent(this, Register::class.java)
            i.putExtra("phone", phone)
            startActivity(i)


        }


        page_7_select_aadhar.setOnClickListener {
            t = 1
            storage()

        }
        page_7_select_10th.setOnClickListener {
            t = 2
            storage()

        }
        page_7_select_12th.setOnClickListener {
            t = 3
            storage()

        }
        page_7_select_birth.setOnClickListener {
            t = 4
            storage()

        }
        page_7_select_community.setOnClickListener {
            t = 5
            storage()

        }
        page_7_select_graduationC.setOnClickListener {
            t = 6
            storage()

        }
        page_7_select_graduationC.setOnClickListener {
            t = 7
            storage()
            //  showFileChooser()
        }
        page_7_select_graduationM.setOnClickListener {
            t = 8
            storage()

        }
        page_7_select_photo.setOnClickListener {
            t = 9
            storage()

        }
        page_7_select_signature.setOnClickListener {
            t = 10
            storage()

        }
        page_7_select_subject.setOnClickListener {
            t = 11
            storage()

        }
        page_7_aadhar_cam.setOnClickListener {
            t = 1
            camera()

        }
        page_7_10th_cam.setOnClickListener {
            t = 2
            camera()

        }
        page_7_12th_cam.setOnClickListener {
            t = 3
            camera()

        }
        page_7_birth_cam.setOnClickListener {
            t = 4
            camera()

        }
        page_7_community_cam.setOnClickListener {
            t = 5
            camera()

        }
        page_7_graduationC_cam.setOnClickListener {
            t = 6
            camera()

        }
        page_7_graduationC_cam.setOnClickListener {
            t = 7
            camera()

        }
        page_7_graduationM_cam.setOnClickListener {
            t = 8
            camera()

        }
        page_7_photo_cam.setOnClickListener {
            t = 9
            camera()

        }
        page_7_signature_cam.setOnClickListener {
            t = 10
            camera()

        }
        page_7_subject_cam.setOnClickListener {
            t = 11
            camera()

        }

    }

    private fun camera() {
        val permission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        )
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                10
            )
        } else {

            click()
        }

    }

    private fun storage() {
        val permission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                11
            )
        } else {

            showFileChooser()
        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        if (requestCode == 11) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                showFileChooser()
            } else {
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG)
                    .show()

            }
        } else if (requestCode == 10) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                click()

            } else {
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG)
                    .show()

            }
        }
    }

    private fun showFileChooser() {
        val intent: Intent = intent
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI
            ),
            PICK_IMAGE_REQUEST
        )
    }

    private fun click() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, CLICK_PHOTO)
        }

    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            PICK_IMAGE_REQUEST -> {


                if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
                    filePath = data.data
                    try {

                        bit = MediaStore.Images.Media.getBitmap(contentResolver, filePath)

                        when (t) {
                            1 -> {

                            }
                            2 -> {

                            }
                            3 -> {

                            }
                            4 -> {

                            }
                            5 -> {

                            }
                            6 -> {

                            }
                            7 -> {

                            }
                            8 -> {

                            }
                            9 -> {

                            }
                            10 -> {

                            }
                            11 -> {

                            }
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }

            CLICK_PHOTO -> {

                if (requestCode == CLICK_PHOTO && resultCode == Activity.RESULT_OK) {
                    try {


                        val extras = data?.extras
                        val bitmap = extras?.get("data") as Bitmap


                        when (t) {
                            1 -> {

                            }
                            2 -> {

                            }
                            3 -> {

                            }
                            4 -> {

                            }
                            5 -> {

                            }
                            6 -> {

                            }
                            7 -> {

                            }
                            8 -> {

                            }
                            9 -> {

                            }
                            10 -> {

                            }
                            11 -> {

                            }
                        }

                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                }
            }
        }


    }


}
