package com.example.initialisablekotlin

import com.example.initialisablekotlin.InitialisableEvent.Companion.APP_INIT_COMPLETE
import com.example.initialisablekotlin.dependencies.*

class AppInitialiser : Initialisable() {
    override fun getInitCompleteEvent(): InitialisableEvent {
        return InitialisableEvent(APP_INIT_COMPLETE)
    }

    override fun provideDependencies(): List<Initialisable> {
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
        softInit()
    }
}