package com.example.initialisablekotlin

import android.util.Log

class MLog{

    companion object {
        const val INIT_TAG = "Initialisable"

        fun log(message: String) {
            Log.i(INIT_TAG, message)
        }
    }
}