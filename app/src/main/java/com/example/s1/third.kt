package com.example.s1

//import android.R
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.page_3.*


class third : AppCompatActivity() {

    lateinit var Phone: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_3)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar!!.title = "STET APPLICATION"
        val phone: String = intent.getStringExtra("phone")
        Phone = phone

        page_3_register.setOnClickListener {
            val i = Intent(this, Register::class.java)
            i.putExtra("phone", phone)
            startActivity(i)

        }


        /* val stitchAppClient = Stitch.getDefaultAppClient()  //get stitch client
         stitchAppClient.auth.loginWithCredential(AnonymousCredential())  //anonymous authentication
             .addOnSuccessListener {
                 //message to show that stitch app is connected
                 Log.d(
                     "app",
                     String.format("successfully found:")
                 )
             }
         val mongoClient = stitchAppClient.getServiceClient(
             RemoteMongoClient.factory,
             "mongodb-atlas"
         ) //get mongo client

         val myCollection = mongoClient.getDatabase("test") //create collection by giving
             .getCollection("table")
         val query = Document().append(
             "name",
             Document().append("ritishek", true)
         )
         Log.d(
             "app",
             String.format("successfully found: ")
         )
         val findResults: RemoteFindIterable<*> = myCollection
             .find(query)
             .projection(Document().append("user_id", 1))
             .sort(Document().append("user_id", 1))
         findResults.forEach { item: Any ->
             Log.d(
                 "app",
                 String.format("successfully found:  %s", item.toString())
             )
             Toast.makeText(this, item.toString(), Toast.LENGTH_LONG).show()
         }
         val res: RemoteFindIterable<Document> = myCollection
             .find(Document().append("name", "ritishek"))
             .projection(Document().append("name", 1))
             .sort(Document().append("name", 1))
         res.forEach { item: Any ->
             Toast.makeText(this, item.toString(), Toast.LENGTH_LONG).show()
         }
         /*val i = Intent(this, eleven::class.java)
         startActivity(i)*/*/


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {

                val it = Intent(this, home::class.java)
                it.putExtra("phone", Phone)
                startActivity(it)
                return true
            }
            R.id.status -> {
                val it = Intent(this, Status::class.java)
                it.putExtra("phone", Phone)
                startActivity(it)
                return true
            }
            R.id.Help -> {

                val it = Intent(this, Help::class.java)
                it.putExtra("phone", Phone)
                startActivity(it)
                return true
            }
            R.id.FAQs -> {

                val it = Intent(this, FAQS::class.java)
                it.putExtra("phone", Phone)
                startActivity(it)

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
