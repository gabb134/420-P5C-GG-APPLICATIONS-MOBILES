package ca.qc.cgodin.laboratoire5

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class RecepteurEvenements : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, R.string.reception_evenement, Toast.LENGTH_SHORT).show()
    }
}