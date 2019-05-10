package com.example.initialisablekotlin

import android.support.annotation.UiThread
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class Initialisable{

    private lateinit var dependencies : List<Initialisable>
    private var executingInit : Boolean = false


    var isInitialising: Boolean = false
    var isInitialised: Boolean = false
    var isHardInit: Boolean = false

    fun getName() : String{
        return this.javaClass.simpleName
    }

    @Synchronized private fun setIsInitialising(value : Boolean){
        MLog.log("setting value isInitialising : $value " +getName())
        isInitialising = value
    }

    @Synchronized private fun setIsInitialised(value : Boolean){
        MLog.log("setting value isInitialised : $value " +getName())
        isInitialised = value
        if(isInitialised){
            setIsInitialising(false)
            EventBus.getDefault().post(getInitCompleteEvent())
        }
    }


    fun softInit(){
        if(isInitialised || isInitialising) return
        init()
    }

    fun hardInit(){
        isHardInit = true
        init()
    }

    @Synchronized private fun init() {
        if(isHardInit){

        }else{

            setIsInitialising(true)

            setDependencies()

            initDependencies()

            registerEvents()

            if(areAlldependeciesInititislised()){
                executeInit()
            }
        }

    }

    abstract fun onInit() : Boolean

    @Synchronized private fun executeInit() {

        MLog.log("executeInit method started, status of executingInit $executingInit " + " for " +getName())

        if (executingInit)
            return
        if (onInit()) {
            executingInit = true
            setIsInitialised(true)
        }

        MLog.log("executeInit method complete, status $isInitialised, for " +getName())

    }

    private fun initDependencies() {
        for (dependency in dependencies) {
            if (isHardInit) {
                dependency.hardInit()
            } else {
                dependency.softInit()
            }
        }

    }

    private fun areAlldependeciesInititislised(): Boolean {
        for (initialisable in dependencies) {
            if (!initialisable.isInitialised) {
                return false
            }
        }
        return true
    }

    /**
     * Register listener to listen whenever it be initialised.
     */
    private fun registerEvents() {
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this)
    }

    fun unRegisterEventBus() {
        MLog.log("unRegisterEventBus")
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this)
    }

    abstract fun provideDependencies() : List<Initialisable>

    private fun setDependencies(){
        dependencies = provideDependencies()
    }

    fun getDependencies() : List<Initialisable>{
        return dependencies
    }

    abstract fun getInitCompleteEvent() : InitialisableEvent


    /**
     * @param event : event when any of the Initialisable dependencies is initialised.
     */
    @Subscribe(threadMode = ThreadMode.ASYNC)
    fun onInitialisableEvent(event: InitialisableEvent) {
        val initialisable = event.obj as Initialisable
        synchronized(this) {
            if (getDependencies().contains(initialisable)) {
                MLog.log("Init pubsub received " + event.eventName +" for "+getName())
                if (areAlldependeciesInititislised()) {
                    MLog.log("All dependencies are good. Current status "
                            + isInitialised )
                    if (/*reinitialiseWithDependencies() ||*/ !isInitialised)
                        executeInit()
                }
            }
        }
    }

}