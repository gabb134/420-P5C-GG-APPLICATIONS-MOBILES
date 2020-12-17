package com.example.ggtoolbarnomprenom

import android.R.id.message
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(ggtoolbar as Toolbar?)
        val view = findViewById<View>(R.id.mainConstraintLayout);
        registerForContextMenu(view);
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Toast.makeText(this, getString(R.string.menu_selectionne, item.title), Toast.LENGTH_SHORT)
          //  .show()
        when (item.itemId) {
            R.id.menu_toolbar_call -> {

                if(ActivityCompat.checkSelfPermission(
                        this ,
                        android.Manifest.permission.CALL_PHONE
                    ) != PackageManager.PERMISSION_GRANTED){

                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(android.Manifest.permission.CALL_PHONE), 1
                    )
                }else{

                    val intent = Intent(Intent.ACTION_CALL);
                    intent.data = Uri.parse("tel:514626-8555")
                    startActivity(intent)
                }

            }
            R.id.menu_toolbar_email -> {

                val mIntent = Intent(Intent.ACTION_SEND)
                val message ="allo".toString().trim()
                /*To send an email you need to specify mailto: as URI using setData() method
                and data type will be to text/plain using setType() method*/
                mIntent.data = Uri.parse("mailto:")
                mIntent.type = "text/plain"
                // put recipient email in intent
                /* recipient is put as array because you may wanna send email to multiple emails
                   so enter comma(,) separated emails, it will be stored in array*/


                mIntent.putExtra(Intent.EXTRA_TEXT, message)


                try {
                    //start email intent
                    startActivity(Intent.createChooser(mIntent, "cesarmarrero7@gmail.com"))
                }
                catch (e: Exception){
                    //if any thing goes wrong for example no email client application or any exception
                    //get and show exception message
                    Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                }

            }
            R.id.menu_toolbar_site -> {
                val url = "https://www.cgodin.qc.ca/"
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)

            }
            R.id.menu_toolbar_toast -> {

                Toast.makeText(this, "Upcoming item…", Toast.LENGTH_SHORT).show()
            }



        }
        when(item.title){
            (ggtoolbar as Toolbar).title -> {


            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        info: ContextMenu.ContextMenuInfo?
    ) {
        when (v.id) {

            R.id.mainConstraintLayout -> menuInflater.inflate(R.menu.menu_contextuel, menu)
            else -> super.onCreateContextMenu(menu, v, info)


        }
    }




}