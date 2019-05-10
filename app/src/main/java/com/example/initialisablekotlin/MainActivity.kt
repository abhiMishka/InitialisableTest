package com.example.initialisablekotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppInitialiser()

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onAppInitEventReceived(initialisableEvents: InitialisableEvent){
        when(initialisableEvents.eventName){
            InitialisableEvent.APP_INIT_COMPLETE -> {
                MLog.log("APP_INIT_COMPLETE")
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
