package ca.qc.cgodin.laboratoire7

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_preferences.*

class PreferencesActivity : AppCompatActivity() {
    private val PREF_USERNAME = "USERNAME"
    private val PREF_SEX = "SEX"
    private val GROUPE_HOMME = 0
    private val GROUPE_FEMME = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)
        restaurer()
    }

    fun onClickBtnSauvegarder(v: View) {
        val editPrefs = getPreferences(Context.MODE_PRIVATE).edit()
        val choice = when (rbGroup.checkedRadioButtonId) {
            R.id.rbHomme -> GROUPE_HOMME
            else -> GROUPE_FEMME
        }
        editPrefs.putInt (PREF_SEX, choice)
            .putString(PREF_USERNAME, etUsername.text.toString())
            .apply()

        etUsername.setText("")
        Toast.makeText(this, "Données sauvegardées!", Toast.LENGTH_SHORT).show()
    }

    private fun restaurer() {
        val prefs = getPreferences(Context.MODE_PRIVATE)
        val choice = prefs.getInt(PREF_SEX, 0)
        rbGroup.check (when (choice) {
            GROUPE_HOMME -> R.id.rbHomme
            else -> R.id.rbFemme
        })
        etUsername.setText(prefs.getString(PREF_USERNAME, ""))
    }
}