package com.example.initialisablekotlin.dependencies

import android.os.AsyncTask
import android.os.AsyncTask.THREAD_POOL_EXECUTOR
import com.example.initialisablekotlin.BgTaskPerformer
import com.example.initialisablekotlin.Initialisable
import com.example.initialisablekotlin.InitialisableEvent
import com.example.initialisablekotlin.MLog
import org.greenrobot.eventbus.EventBus

object Dependency5 : Initialisable() {

    override fun onInit(): Boolean {
        MLog.log("onInit : " + getName())
        val bgTask = BgTaskPerformer()
        bgTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,this)
        return true
    }

    override fun getInitCompleteEvent(): InitialisableEvent {
        return InitialisableEvent(InitialisableEvent.DEPENDENCY5_INITIALISED,this)
    }

    override fun provideDependencies(): List<Initialisable> {
        val dependencies : ArrayList<Initialisable> = ArrayList()
        dependencies.add(Dependency6)
        return dependencies
    }
}