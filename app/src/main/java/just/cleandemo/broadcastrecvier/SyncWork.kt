package just.cleandemo.broadcastrecvier

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class SyncWork(var context: Context, var workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        try {

            return Result.success()

        } catch (e: Exception) {
            return Result.failure()
        }
    }

}
