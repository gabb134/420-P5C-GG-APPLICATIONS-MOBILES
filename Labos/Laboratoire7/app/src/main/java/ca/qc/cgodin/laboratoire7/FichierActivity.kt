package ca.qc.cgodin.laboratoire7

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_fichier.*
import java.io.*

class FichierActivity : AppCompatActivity() {
    private val FILENAME = "donnees.dat"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fichier)
    }

    /*
    * Clic du bouton Ecrire dans un Fichier
    */
    fun onClickBtnEcrireFichier(v: View) {
        lateinit var fos: FileOutputStream
        lateinit var osw: OutputStreamWriter

        try {
            fos = openFileOutput(FILENAME, Context.MODE_APPEND)
            osw = OutputStreamWriter(fos)
            osw.write(edTextFich.text.toString())
            osw.flush()
            Toast.makeText(this, "Données insérées dans le fichier.", Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            Toast.makeText(this, "Données non insérées", Toast.LENGTH_SHORT).show()
        } finally {
            try {
                edTextFich.setText("")
                osw.close()
                fos.close()

            } catch (e: IOException) {
                Toast.makeText(this, "Données non insérées ", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun onClickBtnLireFichier(v: View) {
        lateinit var fis: FileInputStream
        lateinit var isr: InputStreamReader
        val inputBuffer = CharArray(255)
        lateinit var data: String
        try {
            fis = openFileInput(FILENAME)
            isr = InputStreamReader(fis)
            isr.read(inputBuffer)
            data = String(inputBuffer)
            Toast.makeText(this, data.toString(), Toast.LENGTH_SHORT).show()
        }catch(e: Exception){
            Toast.makeText(this, "Fichier non lu", Toast.LENGTH_SHORT).show()
        }
        finally {
            edTextFich.setText("")
        }
    }

}