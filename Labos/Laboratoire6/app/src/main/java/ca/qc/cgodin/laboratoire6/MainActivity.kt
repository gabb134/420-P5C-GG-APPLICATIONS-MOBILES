package ca.qc.cgodin.laboratoire6

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_coloring.*

class MainActivity : AppCompatActivity(), ColoringFragment.OnColoringFragmentInteractionListener,
    ColoredFragment.OnColoredFragmentInteractionListener {

    private lateinit var coloringFragment: ColoringFragment
    private lateinit var coloredFragment: ColoredFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("MainActivity ", "dans ${object {}.javaClass.enclosingMethod.name}")

        coloringFragment =
            supportFragmentManager.findFragmentById(R.id.coloringFragment) as ColoringFragment
        coloredFragment =
            supportFragmentManager.findFragmentById(R.id.coloredFragment) as ColoredFragment

    }

    override fun onStart() {
        super.onStart()
        Log.i("MainActivity ", "dans ${object {}.javaClass.enclosingMethod.name}")
    }

    override fun onResume() {
        super.onResume()
        Log.i("MainActivity ", "dans ${object {}.javaClass.enclosingMethod.name}")

    }

    override fun onPause() {
        super.onPause()
        Log.i("MainActivity ", "dans ${object {}.javaClass.enclosingMethod.name}")
    }

    override fun onStop() {
        super.onStop()
        Log.i("MainActivity ", "dans ${object {}.javaClass.enclosingMethod.name}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity ", "dans ${object {}.javaClass.enclosingMethod.name}")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("MainActivity ", "dans ${object {}.javaClass.enclosingMethod.name}")
    }

    override fun onSendColorFragmentInteraction(couleur: String) {
        Log.i(javaClass.simpleName, "onSendColorFragmentInteraction")


        if(couleur=="Bleu"){

            coloredFragment.setLayoutBackgroundColor(Color.BLUE)
        }
        else if(couleur=="Rouge"){
            coloredFragment.setLayoutBackgroundColor(Color.RED)
        }
        else if(couleur=="Vert"){
            coloredFragment.setLayoutBackgroundColor(Color.GREEN)
        }

        else if(couleur=="Jaune"){
            coloredFragment.setLayoutBackgroundColor(Color.YELLOW)
        }

        else if(couleur=="Cyan"){
            coloredFragment.setLayoutBackgroundColor(Color.CYAN)
        }

        else if(couleur=="Noir"){
            coloredFragment.setLayoutBackgroundColor(Color.BLACK)
        }

        else if(couleur=="Gris"){
            coloredFragment.setLayoutBackgroundColor(Color.GRAY)
        }


        // coloredFragment.setLayoutBackgroundColor(bgColor)
    }

    override fun onSendTextFragmentInteraction(message: String) {
        textViewReponse.text = message;
    }


}