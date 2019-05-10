package com.example.initialisablekotlin.dependencies

import android.os.AsyncTask
import com.example.initialisablekotlin.BgTaskPerformer
import com.example.initialisablekotlin.Initialisable
import com.example.initialisablekotlin.InitialisableEvent
import com.example.initialisablekotlin.MLog
import org.greenrobot.eventbus.EventBus

object Dependency1 : Initialisable(){


    override fun onInit(): Boolean {
        MLog.log("onInit : " +getName())
        val bgTask = BgTaskPerformer()
        bgTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,this)
        return false
    }

    override fun getInitCompleteEvent(): InitialisableEvent {
        return InitialisableEvent(InitialisableEvent.DEPENDENCY1_INITIALISED,this)
    }

    override fun provideDependencies(): List<Initialisable> {
        val dependencies : ArrayList<Initialisable> = ArrayList()
        dependencies.add(Dependency2)
        return dependencies
    }

    init {
    }


}