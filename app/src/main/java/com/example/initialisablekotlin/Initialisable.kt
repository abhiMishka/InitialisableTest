package com.example.initialisablekotlin

import android.util.Log
import org.greenrobot.eventbus.EventBus

abstract class Initialisable{

    private lateinit var dependencies : List<Initialisable>

    companion object{
        const val INIT_TAG = "Initialisable"
    }

    var isInitialising: Boolean = false
    var isInitialised: Boolean = false
    var isHardInit: Boolean = false

    fun setIsInitialising(value : Boolean){
        MLog.log("setting value isInitialising : " +value)
        isInitialising = value
    }

    fun setIsInitialised(value : Boolean){
        MLog.log("setting value isInitialised : " +value)
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

    private fun init() {
        if(isHardInit){

        }else{

            setIsInitialising(true)

            setDependencies()

            initDependencies()

            registerEvents()

            if(areAlldependeciesInititislised()){
//                executeInit()
            }


        }

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

    fun setDependencies(){
        dependencies = provideDependencies()
    }

    fun getDependencies() : List<Initialisable>{
        return dependencies
    }

    abstract fun getInitCompleteEvent() : InitialisableEvent



}