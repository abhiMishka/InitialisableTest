package com.example.initialisablekotlin

import com.example.initialisablekotlin.InitialisableEvent.Companion.APP_INIT_COMPLETE
import com.example.initialisablekotlin.dependencies.*
import org.greenrobot.eventbus.EventBus

class AppInitialiser : Initialisable() {

    override fun onInit(): Boolean {
        MLog.log("main onInit")
        EventBus.getDefault().post(InitialisableEvent(APP_INIT_COMPLETE,this))
        return true
    }

    override fun getInitCompleteEvent(): InitialisableEvent {
        MLog.log("main getInitCompleteEvent")

        return InitialisableEvent(APP_INIT_COMPLETE,this)
    }

    override fun provideDependencies(): List<Initialisable> {
        MLog.log("main provideDependencies")

        val dependencies : ArrayList<Initialisable> = ArrayList()

        dependencies.add(Dependency1)
        dependencies.add(Dependency2)
        dependencies.add(Dependency3)
        dependencies.add(Dependency4)
        dependencies.add(Dependency5)
        dependencies.add(Dependency6)

        return dependencies
    }

    init {
        MLog.log("softInit " + getName())
        softInit()
    }
}