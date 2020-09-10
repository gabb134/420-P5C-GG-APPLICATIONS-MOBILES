package com.example.laboratoire3

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
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

        txtActifs.text = intActifs.toString() + "$"

        btnJouer.setOnClickListener(this)

        rGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, chekedId ->
                val radio: RadioButton = findViewById(chekedId)
                // Toast.makeText(applicationContext, "On cheked change : ${radio.text}", Toast.LENGTH_SHORT).show()
            }
        )

    }
   /* override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        Log.i("MainActivity ", "dans ${object {}.javaClass.enclosingMethod.name}")
        Toast.makeText(this, "dans ${object {}.javaClass.enclosingMethod.name}",Toast.LENGTH_LONG).show()



    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("MainActivity ", "dans ${object {}.javaClass.enclosingMethod.name}")
        Toast.makeText(this, "dans ${object {}.javaClass.enclosingMethod.name}",Toast.LENGTH_LONG).show()

    }*/

    fun onClickCodeSecret(view: View){
        val action = intent.action;
        var binCodeSecret : Boolean = false;
        if(editTextTextPassword2.text.toString()=="123"){
            Toast.makeText(
                applicationContext, "Code secret est bon!",
                Toast.LENGTH_SHORT
            ).show()
            Log.i(
                action,
                " Code secret : ${editTextTextPassword2.text}"
            )
            intActifs += 100;
            txtActifs.text = intActifs.toString() +"$"
            editTextTextPassword2.text.clear();

        }else{
            Toast.makeText(
                applicationContext, "Mauvais code secret",
                Toast.LENGTH_SHORT
            ).show()
            editTextTextPassword2.text.clear();
        }
        if(intActifs>5){
            btnJouer.isEnabled = true;
            rbUnDollar.isEnabled = true;
            rbDeuxDollars.isEnabled = true;
            rbCinqDollars.isEnabled = true;
        }

    }

    fun casseCocher(argentGagnerUn: Int,argentGagnerDeux: Int,argentGagnerCinq: Int ,random: Int){//voir comment faire

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
                        if(cbCasseCou.isChecked){
                            Toast.makeText(applicationContext, "case est coché!", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            Toast.makeText(applicationContext, "case n'est pas coché!", Toast.LENGTH_SHORT).show()
                           // casseCocher(argent: Int,random: Int)

                        }

                        if (lstImage[number1] == lstImage[number2] && lstImage[number1] == lstImage[number3] && lstImage[number3] == lstImage[number2]) {
                            intActifs += 25;
                            Log.i(action, " 3 pareils, valeurs de intActifs : ${intActifs}")
                            Toast.makeText(applicationContext, "Gangné ${25}$",Toast.LENGTH_SHORT).show()
                        } else if (lstImage[number1] == lstImage[number2] || lstImage[number1] == lstImage[number3] || lstImage[number3] == lstImage[number2]) {
                            intActifs += 1;
                            Log.i(action," Au moins 2 pareils, valeurs de intActifs : ${intActifs}")
                            Toast.makeText(applicationContext, "Gangné ${1}$",Toast.LENGTH_SHORT).show()
                        } else {
                            Log.i(action, " Aucun pareil, valeurs de intActifs : ${intActifs}")
                            intActifs -= 1;
                            //    Toast.makeText(applicationContext, "Perdu ${1}$",Toast.LENGTH_SHORT).show()
                        }


                    } else if (radio.text == "2$") { //pour une mise de 2$
                        if (lstImage[number1] == lstImage[number2] && lstImage[number1] == lstImage[number3] && lstImage[number3] == lstImage[number2]) {
                            intActifs += 50;
                            Log.i(action, " 3 pareils, valeurs de intActifs : ${intActifs}")
                            Toast.makeText(applicationContext, "Gangné ${50}$",Toast.LENGTH_SHORT).show()
                        } else if (lstImage[number1] == lstImage[number2] || lstImage[number1] == lstImage[number3] || lstImage[number3] == lstImage[number2]) {
                            intActifs += 2;
                            Log.i(action," Au moins 2 pareils, valeurs de intActifs : ${intActifs}")
                            Toast.makeText(applicationContext, "Gangné ${2}$",Toast.LENGTH_SHORT).show()
                        } else {
                            Log.i(action, " Aucun pareil, valeurs de intActifs : ${intActifs}")
                            intActifs -= 2;
                            //  Toast.makeText(applicationContext, "Perdu ${2}$",Toast.LENGTH_SHORT).show()
                        }

                    } else {//pour une mise de 5$

                        if (lstImage[number1] == lstImage[number2] && lstImage[number1] == lstImage[number3] && lstImage[number3] == lstImage[number2]) {
                            intActifs += 250;
                            Log.i(action, " 3 pareils, valeurs de intActifs : ${intActifs}")
                            Toast.makeText(applicationContext, "Gangné ${250}$",Toast.LENGTH_SHORT).show()
                        } else if (lstImage[number1] == lstImage[number2] || lstImage[number1] == lstImage[number3] || lstImage[number3] == lstImage[number2]) {
                            intActifs += 5;
                            Log.i(action," Au moins 2 pareils, valeurs de intActifs : ${intActifs}")
                            Toast.makeText(applicationContext, "Gangné ${5}$",Toast.LENGTH_SHORT).show()
                        } else {
                            Log.i(action, " Aucun pareil, valeurs de intActifs : ${intActifs}")
                            intActifs -= 5;
                            // Toast.makeText(applicationContext, "Perdu ${5}$",Toast.LENGTH_SHORT).show()
                        }
                    }
                    //Toast.makeText(applicationContext,"On button click : ${radio.text}",
                    //   Toast.LENGTH_SHORT).show()
                }

                //si on a pu d'actifs, on désactive le tnjouer et les radio button
                if(intActifs<5){

                    rbCinqDollars.isEnabled = false;
                    rbCinqDollars.isChecked = false;
                  //  btnJouer.isEnabled = false;
                    btnJouer.isEnabled = false;
                }
                if(intActifs<2){

                    rbDeuxDollars.isEnabled = false;
                    //   btnJouer.isEnabled = false;
                    btnJouer.isEnabled = false;
                }
                if(intActifs<1){
                    // btnJouer.isEnabled = false;
                    rbUnDollar.isEnabled = false;
                    btnJouer.isEnabled = false;
                }



            }

        }


        txtActifs.text = intActifs.toString() + "$" //affichage de l'actifs apres avoir miser
    }


    fun radio_button_click(view: View) {
        val radio: RadioButton = findViewById(rGroup.checkedRadioButtonId)

        if(radio.text=="1$"){
     //   Toast.makeText(
         //   applicationContext, "On click : ${radio.text}",
         //   Toast.LENGTH_SHORT
       // ).show()
        btnJouer.isEnabled = true;
    }
        if(radio.text=="2$"){
          //  Toast.makeText(
           //     applicationContext, "On click : ${radio.text}",
           //     Toast.LENGTH_SHORT
          //  ).show()
            btnJouer.isEnabled = true;
        }
        if(radio.text=="5$"){
        //    Toast.makeText(
            //    applicationContext, "On click : ${radio.text}",
             //   Toast.LENGTH_SHORT
           // ).show()
           // btnJouer.isEnabled = true;
            btnJouer.isEnabled = true;
        }


    }

}