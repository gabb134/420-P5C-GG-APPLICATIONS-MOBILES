package ca.qc.cgodin.laboratoire7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

     fun onClick(view: View?){
        when(view){
            btnActivitePref->{
                val intent = Intent(this@MainActivity, PreferencesActivity::class.java)
                startActivity(intent)
            }
            btnActiviteFichier->{
                val intent = Intent(this@MainActivity, FichierActivity::class.java)
                startActivity(intent)
            }
            btnActiviteBD->{
                val intent = Intent(this@MainActivity, BDActivity::class.java)
                startActivity(intent)
            }
        }
    }
}