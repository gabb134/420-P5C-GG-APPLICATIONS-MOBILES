package ca.qc.cgodin.laboratoire6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
    }
     fun onClick(view: View?) {
        when(view){
            btnStaticFragments->{
                val intent = Intent(this@WelcomeActivity, StaticHostActivity::class.java)
                startActivity(intent)
            }
            btnDynamicFragments->{
                val intent = Intent(this@WelcomeActivity, DynamicHostActivity::class.java)
                startActivity(intent)
            }
        }

    }
}