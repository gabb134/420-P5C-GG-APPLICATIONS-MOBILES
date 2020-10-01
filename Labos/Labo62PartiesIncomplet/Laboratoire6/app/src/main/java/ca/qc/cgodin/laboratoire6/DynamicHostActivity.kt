package ca.qc.cgodin.laboratoire6

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.fragment_coloring.*

class DynamicHostActivity : AppCompatActivity(),
    ColoringFragment.OnColoringFragmentInteractionListener,
    ColoredFragment.OnColoredFragmentInteractionListener {

    private lateinit var dynamicColoredFragment: ColoredFragment
    private lateinit var dynamicColoringFragment: ColoringFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic_host)

        dynamicColoringFragment = ColoringFragment.newInstance("", "")
        dynamicColoredFragment = ColoredFragment.newInstance("", "")

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.topLinearLayout, dynamicColoringFragment)
        fragmentTransaction.add(R.id.bottomLinearLayout, dynamicColoredFragment)
        fragmentTransaction.commit()

        // setContentView(R.layout.activity_dynamic_host)
    }

    override fun onResume() {
        super.onResume()
        dynamicColoringFragment.enableReplaceFragmentButton()
    }

    override fun onSendColorFragmentInteraction(couleur: String) {
        if (couleur == "Bleu") {

            dynamicColoredFragment.setLayoutBackgroundColor(Color.BLUE)
        } else if (couleur == "Rouge") {
            dynamicColoredFragment.setLayoutBackgroundColor(Color.RED)
        } else if (couleur == "Vert") {
            dynamicColoredFragment.setLayoutBackgroundColor(Color.GREEN)
        } else if (couleur == "Jaune") {
            dynamicColoredFragment.setLayoutBackgroundColor(Color.YELLOW)
        } else if (couleur == "Cyan") {
            dynamicColoredFragment.setLayoutBackgroundColor(Color.CYAN)
        } else if (couleur == "Noir") {
            dynamicColoredFragment.setLayoutBackgroundColor(Color.BLACK)
        } else if (couleur == "Gris") {
            dynamicColoredFragment.setLayoutBackgroundColor(Color.GRAY)
        }

    }

    override fun onChangeFragment() {
        val replacingFragment = ReplacingFragment.newInstance("", "")
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.bottomLinearLayout, replacingFragment)
        //fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onSendTextFragmentInteraction(messageRemerciements: String) {
        textViewReponse.text = messageRemerciements;
    }
}