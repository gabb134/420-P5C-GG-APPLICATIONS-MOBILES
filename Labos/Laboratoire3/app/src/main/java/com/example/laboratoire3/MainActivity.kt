package com.example.laboratoire3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), View.OnClickListener {

    //creation de la liste des images et ajout des images dans la liste
    var lstImage = arrayListOf<Int>(
        R.drawable.androidlogo,
        R.drawable.applelogo,
        R.drawable.bar,
        R.drawable.cerise,
        R.drawable.cloche,
        R.drawable.gg,
        R.drawable.orange,
        R.drawable.raisinpng,
        R.drawable.sept,
        R.drawable.windows
    )
    var intCodeSecet: Int = 123;
    var intActifs: Int = 100;
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

        txtActifs.text = intActifs.toString() + "$"

        btnJouer.setOnClickListener(this)

        rGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, chekedId ->
                val radio: RadioButton = findViewById(chekedId)
                // Toast.makeText(applicationContext, "On cheked change : ${radio.text}", Toast.LENGTH_SHORT).show()
            }
        )


    }

    override fun onClick(view: View?) {
        when (view) {
            btnJouer -> {
                // faire apparaitre les images aléatoirement...

                val action = intent.action;
                //  Toast.makeText(this, " Vous avez cliquer le button jouer qui se trouve dans: " + action, Toast.LENGTH_LONG).show();
                var ran = Random();
                var number1 = ran.nextInt(10)
                var number2 = ran.nextInt(10)
                var number3 = ran.nextInt(10)
                imageView.setImageResource(lstImage[number1]);
                imageView2.setImageResource(lstImage[number2]);
                imageView3.setImageResource(lstImage[number3]);
                //les radiobuttons
                var id: Int = rGroup.checkedRadioButtonId
                if (id != 1) {
                    val radio: RadioButton = findViewById(id)

                    if (radio.text == "1$") {//pour une mise de 1$
                        Toast.makeText(
                            applicationContext, "Mise de 1$",
                            Toast.LENGTH_SHORT
                        ).show()
                        //Log.i( action, " Numero aléatoire : ${lstImage[number1]}  et ${lstImage[number2]}");
                        // dblActifs = dblActifs+1 ;
                        if (lstImage[number1] == lstImage[number2] && lstImage[number1] == lstImage[number3] && lstImage[number3] == lstImage[number2]) {
                            intActifs += 25;
                            Log.i(action, " 3 pareils, valeurs de intActifs : ${intActifs}")
                        } else if (lstImage[number1] == lstImage[number2] || lstImage[number1] == lstImage[number3] || lstImage[number3] == lstImage[number2]) {
                            intActifs += 1;
                            Log.i(
                                action,
                                " Au moins 2 pareils, valeurs de intActifs : ${intActifs}"
                            )
                        } else {
                            Log.i(action, " Aucun pareil, valeurs de intActifs : ${intActifs}")
                            intActifs -= 1;
                        }


                    } else if (radio.text == "2$") { //pour une mise de 2$
                        Toast.makeText(
                            applicationContext, "Mise de 2$",
                            Toast.LENGTH_SHORT
                        ).show()

                        if (lstImage[number1] == lstImage[number2] && lstImage[number1] == lstImage[number3] && lstImage[number3] == lstImage[number2]) {
                            intActifs += 50;
                            Log.i(action, " 3 pareils, valeurs de intActifs : ${intActifs}")
                        } else if (lstImage[number1] == lstImage[number2] || lstImage[number1] == lstImage[number3] || lstImage[number3] == lstImage[number2]) {
                            intActifs += 2;
                            Log.i(
                                action,
                                " Au moins 2 pareils, valeurs de intActifs : ${intActifs}"
                            )
                        } else {
                            Log.i(action, " Aucun pareil, valeurs de intActifs : ${intActifs}")
                            intActifs -= 2;
                        }

                    } else {//pour une mise de 5$
                        Toast.makeText(
                            applicationContext, "Mise de 5$",
                            Toast.LENGTH_SHORT
                        ).show()
                        if (lstImage[number1] == lstImage[number2] && lstImage[number1] == lstImage[number3] && lstImage[number3] == lstImage[number2]) {
                            intActifs += 250;
                            Log.i(action, " 3 pareils, valeurs de intActifs : ${intActifs}")
                        } else if (lstImage[number1] == lstImage[number2] || lstImage[number1] == lstImage[number3] || lstImage[number3] == lstImage[number2]) {
                            intActifs += 5;
                            Log.i(
                                action,
                                " Au moins 2 pareils, valeurs de intActifs : ${intActifs}"
                            )
                        } else {
                            Log.i(action, " Aucun pareil, valeurs de intActifs : ${intActifs}")
                            intActifs -= 5;
                        }
                    }
                    //Toast.makeText(applicationContext,"On button click : ${radio.text}",
                    //   Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(
                        applicationContext, "On button click : nothing selected",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
        txtActifs.text = intActifs.toString() + "$" //affichage de l'actifs apres avoir miser
    }


    fun radio_button_click(view: View) {
        val radio: RadioButton = findViewById(rGroup.checkedRadioButtonId)
        Toast.makeText(
            applicationContext, "On click : ${radio.text}",
            Toast.LENGTH_SHORT
        ).show()
    }

}