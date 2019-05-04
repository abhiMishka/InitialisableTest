package com.example.initialisablekotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Initialisable().
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onAppInitEventReceived(initialisableEvents: InitialisableEvent){
        when(initialisableEvents.eventName){
            InitialisableEvent.DEPENDENCY1_INITIALISED -> {
                MLog.log("Dep1 initialised")
            }
            InitialisableEvent.DEPENDENCY2_INITIALISED -> {
                MLog.log("Dep2 initialised")
            }
            InitialisableEvent.DEPENDENCY3_INITIALISED -> {
                MLog.log("Dep3 initialised")
            }
            InitialisableEvent.DEPENDENCY4_INITIALISED -> {
                MLog.log("Dep4 initialised")
            }
            InitialisableEvent.DEPENDENCY5_INITIALISED -> {
                MLog.log("Dep5 initialised")
            }
            InitialisableEvent.DEPENDENCY6_INITIALISED -> {
                MLog.log("Dep6 initialised")
            }
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }
}
