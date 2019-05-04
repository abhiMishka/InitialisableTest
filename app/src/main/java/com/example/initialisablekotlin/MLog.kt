package com.example.initialisablekotlin

import android.util.Log

class MLog{

    companion object {
        fun log(message: String) {
            Log.i(Initialisable.INIT_TAG, message)
        }
    }
}