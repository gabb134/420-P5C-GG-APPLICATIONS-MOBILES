package ca.qc.godin.laboratoire2

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class DestinataireActivity : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destinataire)
    }
}


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var CLE_COULEUR:String = "COULEUR"
    private var mCouleur:Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("MainActivity ", "dans ${object {}.javaClass.enclosingMethod.name}")
        btnFinish.setOnClickListener { finish() }
        mCouleur = Random(System.currentTimeMillis()).nextInt()
        btnExplicite.setOnClickListener(this)

    }
    override fun onStart() {
        super.onStart()
        Log.i("MainActivity ", "dans ${object {}.javaClass.enclosingMethod.name}")
    }
    override fun onResume() {
        super.onResume()
        Log.i("MainActivity ", "dans ${object {}.javaClass.enclosingMethod.name}")
        layoutBase.setBackgroundColor(mCouleur)

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
    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        Log.i("MainActivity ", "dans ${object {}.javaClass.enclosingMethod.name}")
        Toast.makeText(this, "dans ${object {}.javaClass.enclosingMethod.name}",Toast.LENGTH_LONG).show()

        if(checkBox.isChecked){
            outState.putInt(CLE_COULEUR, (layoutBase.background as ColorDrawable).color)
        }

    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("MainActivity ", "dans ${object {}.javaClass.enclosingMethod.name}")
        Toast.makeText(this, "dans ${object {}.javaClass.enclosingMethod.name}",Toast.LENGTH_LONG).show()

        if(checkBox.isChecked) {
            mCouleur = savedInstanceState.getInt(CLE_COULEUR)
        }

    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }


}
