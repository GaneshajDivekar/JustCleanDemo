package just.cleandemo.broadcastrecvier

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.work.*
import java.util.concurrent.TimeUnit

class ConnectivityReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {
        try {
            if (isConnected(context!!)) {
                Toast.makeText(context, "Internet Connected", Toast.LENGTH_SHORT).show()
                val workRequest = OneTimeWorkRequest.Builder(SyncWork::class.java)
                    .setConstraints(
                        Constraints.Builder()
                            .setRequiredNetworkType(NetworkType.CONNECTED)
                            .build())
                    .setInitialDelay(1, TimeUnit.SECONDS)
                    .build()
            } else {

                Toast.makeText(context, "No Internet Connected", Toast.LENGTH_SHORT).show()

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun isConnected(context: Context): Boolean {
        val manager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                ?: return false
        val info = manager.activeNetworkInfo
        return info != null && info.isConnected
    }

}