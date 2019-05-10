package com.example.initialisablekotlin

data class InitialisableEvent(val eventName : String,val obj : Any){

    companion object {
        const val DEPENDENCY1_INITIALISED = "DEPENDENCY1_INITIALISED"
        const val DEPENDENCY2_INITIALISED = "DEPENDENCY2_INITIALISED"
        const val DEPENDENCY3_INITIALISED = "DEPENDENCY3_INITIALISED"
        const val DEPENDENCY4_INITIALISED = "DEPENDENCY4_INITIALISED"
        const val DEPENDENCY5_INITIALISED = "DEPENDENCY5_INITIALISED"
        const val DEPENDENCY6_INITIALISED = "DEPENDENCY6_INITIALISED"

        const val APP_INIT_COMPLETE       = "APP_INIT_COMPLETE"
    }

}