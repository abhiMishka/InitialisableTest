package com.example.initialisablekotlin.dependencies

import com.example.initialisablekotlin.Initialisable
import com.example.initialisablekotlin.InitialisableEvent

object Dependency6 : Initialisable() {
    override fun getInitCompleteEvent(): InitialisableEvent {
        return InitialisableEvent(InitialisableEvent.DEPENDENCY6_INITIALISED)
    }

    override fun provideDependencies(): List<Initialisable> {
        val dependencies : ArrayList<Initialisable> = ArrayList()
        return dependencies
    }
}