package ca.qc.cgodin.laboratoire5

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG = "Bouton est cliqué"
    private var mService: TestService? = null

    private val mConnexion = object : ServiceConnection {
        override fun onServiceConnected(arg0: ComponentName, arg1: IBinder) {
            Toast.makeText(
                this@MainActivity,
                "dans ${object {}.javaClass.enclosingMethod.name}",
                Toast.LENGTH_SHORT
            )
                .show()
            mService = (arg1 as TestService.MonServiceBinder).getService()
            btnConnecter.setText(R.string.Connecter)
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            Toast.makeText(
                this@MainActivity,
                "dans ${object {}.javaClass.enclosingMethod.name}",
                Toast.LENGTH_SHORT
            ).show()
            mService = null
            btnConnecter.setText(R.string.Connecter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun clickHandler(view: View) {
        Log.i(TAG, (view as Button).text.toString())
        Toast.makeText(
            this,
            "dans ${object {}.javaClass.enclosingMethod.name}",
            Toast.LENGTH_SHORT
        )
            .show()

        val intent = Intent(this, TestService::class.java)
        when (view.id) {
            R.id.btnDemarrer -> {
                startService(intent)
            }
            R.id.btnArreter -> {
                stopService(intent)
            }
            R.id.btnConnecter -> {
                if (mService == null) {
                    bindService(intent, this.mConnexion, Context.BIND_AUTO_CREATE)
                } else {
                    unbindService(mConnexion)
                    mService = null
                    (view as Button).setText(R.string.Connecter)
                }
            }
            R.id.btnStopSelf -> {
                intent.putExtra(TestService.CLE_STOP, true)
                startService(intent)
            }
            R.id.btnAjouterNotification -> {
                val mLogo =
                    BitmapFactory.decodeResource(resources, R.drawable.ic_baseline_star_24)
                val patternVibrations =
                    longArrayOf(0, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50)
                val notificationChannel: NotificationChannel
                var notificationBuilder: Notification.Builder // Création de la notification. Noter cette nouvelle façon de créer un objet
                val notificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val channelId = "5PCLabo5NotifChannel001"
                    val description = "Canal de notification du Labo 5"
                    notificationChannel = NotificationChannel(
                        channelId,
                        description, NotificationManager.IMPORTANCE_HIGH
                    )
                    notificationChannel.enableLights(true)
                    notificationChannel.lightColor = Color.GREEN
                    notificationChannel.enableVibration(true)

                    notificationManager.createNotificationChannel(notificationChannel)

                    notificationBuilder = Notification.Builder(this, channelId)
                } else {
                    notificationBuilder = Notification.Builder(this).setVibrate(patternVibrations)
                }

                //intent pour la notification
                val notificationIntent: Intent = Intent(
                    this,
                    NotificationActivity::class.java
                );
                notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //Noter l'utilisation d'un PendingIntent (Intention en attente).

                val pendingIntent: PendingIntent = PendingIntent.getActivity(
                    this,
                    0, notificationIntent, 0
                ); notificationBuilder.setLargeIcon(mLogo)
                    .setAutoCancel(true) //Notification disparait quand L'utilisateur la touche
                    .setSmallIcon(R.drawable.ic_baseline_flag_24)
                    .setContentTitle(getString(R.string.notification_titre))
                    .setContentText(getString(R.string.notification_text))
                    .setNumber(1).setContentIntent(pendingIntent);

                val notification = notificationBuilder.build()
                Toast.makeText(
                    this, when (notification) {
                        null -> R.string.notification_erreur
                        else -> R.string.notification_ajoutee
                    }, Toast.LENGTH_LONG
                ).show()
                if (notification != null) {
                    notificationManager.notify(0, notification)
                }
            }
            R.id.btnEnvoyerEvenement -> {
                val intent = Intent(this, RecepteurEvenements::class.java)
                sendBroadcast (intent)
            }


        }


    }
}