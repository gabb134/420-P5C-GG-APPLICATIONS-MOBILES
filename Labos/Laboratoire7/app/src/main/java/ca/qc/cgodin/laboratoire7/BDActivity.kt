package ca.qc.cgodin.laboratoire7

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.view.View
import android.widget.TextView
import android.widget.Toast

class BDActivity : AppCompatActivity() {
    class BDAssistant(context: Context) : SQLiteOpenHelper(context, NOM_BASE, null, VERSION) {
        override fun onCreate(arg0: SQLiteDatabase) {
            arg0.execSQL(
                String.format(
                    "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT)",
                    NOM_TABLE,
                    BaseColumns._ID,
                    CHAMP_NOM,
                    CHAMP_PRENOM
                )
            )
        }

        override fun onUpgrade(arg0: SQLiteDatabase, arg1: Int, arg2: Int) {}

        companion object {
            val VERSION = 1
            val NOM_BASE = "maBase"
            val NOM_TABLE = "maTable"
            val CHAMP_NOM = "nom"
            val CHAMP_PRENOM = "prenom"
        }
    } // fin de la classe BDAssistant

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView (R.layout.activity_b_d)
    }

    private fun insererDonnees() {
        val bdAssistant = BDAssistant(this)
        val bd = bdAssistant.writableDatabase
        var valeurs = ContentValues()
        valeurs.put(BDAssistant.CHAMP_NOM, "Nom1_"+System.currentTimeMillis())
        valeurs.put(BDAssistant.CHAMP_PRENOM, "Prénom1_"+System.currentTimeMillis())
        bd.insertOrThrow(BDAssistant.NOM_TABLE, null, valeurs)
        valeurs = ContentValues()
        valeurs.put(BDAssistant.CHAMP_NOM, "Nom2_"+System.currentTimeMillis())
        valeurs.put(BDAssistant.CHAMP_PRENOM, "Prénom2_"+System.currentTimeMillis())
        bd.insertOrThrow(BDAssistant.NOM_TABLE, null, valeurs)
        bd.close()
        bdAssistant.close()
    }

    private fun lireBD() {
        val bdAssistant = BDAssistant(this)
        val bd = bdAssistant.readableDatabase
        val noms = StringBuffer("Noms et Prénoms :\n\n")

        val curseur = bd.query(
            BDAssistant.NOM_TABLE,
            arrayOf(BDAssistant.CHAMP_NOM, BDAssistant.CHAMP_PRENOM),
            null,
            null,
            null,
            null,
            "${BDAssistant.CHAMP_NOM} desc"
        )
        while (curseur.moveToNext()) {
            val nomEtPrenom =
                curseur.getString(0) + " " + curseur.getString(1)
                noms.append(nomEtPrenom + "\n\n")
        }
        bd.close()
        bdAssistant.close ()
        val tvNoms = findViewById<View>(R.id.tvData) as TextView
        tvNoms.text = noms
        curseur.close()
    }

    fun onClickBtnCreer(v: View) {
        insererDonnees()
        Toast.makeText (this, "Données insérées avec succès", Toast.LENGTH_SHORT).show()
    }

    fun onClickBtnAfficherDonnees(v: View) {
        lireBD()
        Toast.makeText (this, "Données lues avec succès", Toast.LENGTH_SHORT).show()
    }
}