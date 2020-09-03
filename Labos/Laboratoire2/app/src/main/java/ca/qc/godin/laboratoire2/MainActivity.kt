package ca.qc.godin.laboratoire2

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random




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
        btnRes.setOnClickListener (this)
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

    override fun onClick(view: View?) {
        when(view){
            btnExplicite ->{
                val intent = Intent(this@MainActivity, DestinataireActivity::class.java)
                startActivity(intent)
            }
            btnRes ->{
                val intent = Intent(this, DestinataireResActivity::class.java)
                startActivityForResult(intent, 1)
            }
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.i("MainActivity ", "dans ${object {}.javaClass.enclosingMethod.name}")
        when (requestCode) {
            1 -> {
                val resTxt = if (resultCode == Activity.RESULT_OK) {
                    "RESULT_OK " + DestinataireResActivity.CLE1 + "= " +
                            data?.getStringExtra(DestinataireResActivity.CLE1) + " " +
                            DestinataireResActivity.CLE2 + "= " +
                            data?.getBooleanExtra(DestinataireResActivity.CLE2, false)
                } else
                    "RESULT_CANCELED"
                Toast.makeText(this, resTxt, Toast.LENGTH_LONG).show()
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }



}
