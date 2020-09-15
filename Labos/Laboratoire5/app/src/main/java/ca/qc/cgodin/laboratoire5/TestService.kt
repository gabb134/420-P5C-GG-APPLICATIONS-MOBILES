package ca.qc.cgodin.laboratoire5

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class TestService : Service() {
    inner class MonServiceBinder : Binder() {
        fun getService(): TestService {
            return this@TestService
        }
    }

    companion object {
        val CLE_STOP = "stop"
    }

    private val mBinder: IBinder = MonServiceBinder()
    val TAG: String = TestService::class.java.canonicalName

    override fun onBind(intent: Intent): IBinder {
        Toast.makeText(this, "dans ${object {}.javaClass.enclosingMethod.name}", Toast.LENGTH_SHORT)
            .show()
        afficherNotification(0);
        return mBinder
    }

    override fun onUnbind(intent: Intent): Boolean {
        Toast.makeText(this, "dans ${object {}.javaClass.enclosingMethod.name}", Toast.LENGTH_SHORT)
            .show()
        return true
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "dans ${object {}.javaClass.enclosingMethod.name}")
        Toast.makeText(this, "dans ${object {}.javaClass.enclosingMethod.name}", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(
            this,
            "dans ${object {}.javaClass.enclosingMethod.name}: startId=$startId",
            Toast.LENGTH_SHORT
        ).show()
        val stop = intent?.getBooleanExtra(CLE_STOP, false)
        if (stop!!) {
            Toast.makeText(this, "stopSelf", Toast.LENGTH_SHORT).show()
            stopSelf()
        }
        afficherNotification(startId)

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Toast.makeText(this, "dans ${object {}.javaClass.enclosingMethod.name}", Toast.LENGTH_SHORT)
            .show()
        val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(0)
        super.onDestroy()
    }

    private fun afficherNotification(nb: Int) {
        val mLogo = BitmapFactory.decodeResource(resources, R.drawable.ic_baseline_star_24)
        val patternVibrations = longArrayOf(0, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50)
        val notificationChannel: NotificationChannel
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var notificationBuilder: Notification.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "5PCLabo5NotifChannel002"
            val description = "Canal de notification utilisant les services du Labo 5"
            notificationChannel = NotificationChannel (channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(true)
            notificationManager.createNotificationChannel(notificationChannel)
            notificationBuilder = Notification.Builder(this, channelId)
        } else {
            notificationBuilder = Notification.Builder(this).setVibrate(patternVibrations)
        }
        notificationBuilder.setLargeIcon (mLogo).setOngoing(true) //persistante. ne peut pas être supprimée par l'utilisateur
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(getString(R.string.notification_service_titre))
            .setContentText(getString(R.string.notification_service_text))
            .setNumber(1)
        val notification = notificationBuilder.build()
        if (notification != null) {
            notificationManager.notify(0, notification)
        }
    }

}