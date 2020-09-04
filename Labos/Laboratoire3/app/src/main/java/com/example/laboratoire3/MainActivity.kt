package com.example.laboratoire3

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //initialisation des imageviews
      //  val constraintLayout = findViewById(R.id.imageView1) as ConstraintLayout
        //val imageViewUn = ImageView(this)
        //imageViewUn.setImageResource(R.drawable.androidlogo)
       // constraintLayout.addView(imageViewUn)
       // val constraintLayout = findViewById(R.id.imageView1) as ImageView
      //  val imageView = ImageView(this)
      //  imageView.setImageResource(R.drawable.androidlogo)
     //   constraintLayout.setImageDrawable(imageView)



        btnJouer.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when (view) {
            btnJouer -> {
           // faire apparaitre les images aléatoirement...

                val action = intent.action;
                Toast.makeText(this, " Vous avez cliquer le button jouer qui se trouve dans: " + action, Toast.LENGTH_LONG).show();


            }

        }

    }
}