package ca.qc.godin.laboratoire2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.snackbar.Snackbar



class DestinataireResActivity : AppCompatActivity() {
    companion object{
        val CLE1 = "cle1"
        val CLE2 = "cle2"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destinataire_res)
        setResult(Activity.RESULT_CANCELED)
        val btnRes = findViewById<Button>(R.id.btnResulstat)

        btnRes.setOnClickListener {
            val donnees = Intent()
            donnees.putExtra(CLE1, "allo")
            donnees.putExtra(CLE2, true)
            setResult(RESULT_OK, donnees)
            finish()
        }

    }
}