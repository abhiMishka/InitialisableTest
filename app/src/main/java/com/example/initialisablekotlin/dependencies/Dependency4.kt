package com.example.initialisablekotlin.dependencies

import com.example.initialisablekotlin.Initialisable
import com.example.initialisablekotlin.InitialisableEvent

object Dependency4 : Initialisable() {
    override fun getInitCompleteEvent(): InitialisableEvent {
        return InitialisableEvent(InitialisableEvent.DEPENDENCY4_INITIALISED)
    }

    override fun provideDependencies(): List<Initialisable> {
        val dependencies : ArrayList<Initialisable> = ArrayList()
        dependencies.add(Dependency5)
        return dependencies
    }
}