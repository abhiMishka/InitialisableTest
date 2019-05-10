package com.example.initialisablekotlin

import android.os.AsyncTask
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.annotation.UiThread
import org.greenrobot.eventbus.EventBus
import java.util.concurrent.ThreadLocalRandom

class BgTaskPerformer : AsyncTask<Initialisable, Void, Initialisable>() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun doInBackground(vararg params: Initialisable): Initialisable {
        val sleepTime : Long = ThreadLocalRandom.current().nextInt(1, 5).toLong()
        MLog.log("sleepTime " +sleepTime + " for " +params[0].getName())

        Thread.sleep(sleepTime * 1000)
        return params[0]
    }

    override fun onPostExecute(result: Initialisable) {
        super.onPostExecute(result)

        MLog.log("Done running " +result.getName());

        EventBus.getDefault().post(result.getInitCompleteEvent())
    }

}