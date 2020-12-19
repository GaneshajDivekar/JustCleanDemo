package just.cleandemo.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by ${Ganesh Divekar}
 */
class Utils : Activity() {

    private var utilss: Utils? = null

    fun getUtilsInstance(): Utils? {
        if (utilss == null) {
            utilss = Utils()
        }
        return utilss
    }
    fun isConnected(context: Context): Boolean {
        val manager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                ?: return false
        val info = manager.activeNetworkInfo
        return info != null && info.isConnected
    }
}