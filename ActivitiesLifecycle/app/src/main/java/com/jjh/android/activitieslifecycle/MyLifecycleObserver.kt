package com.jjh.android.helloworld

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyLifecycleObserver : LifecycleObserver {

    companion object {
        private const val TAG = "MyLifecycleObserver"
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun logOnCreate() {
        Log.d(TAG, "logOnCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun logOnStart() {
        Log.d(TAG, "logOnStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun logOnResume() {
        Log.d(TAG, "logOnResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun logOnPause() {
        Log.d(TAG, "logOnPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun logOnStop() {
        Log.d(TAG, "logOnStop")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun logOnDestroy() {
        Log.d(TAG, "logOnDestroy")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun logOnAny() {
        Log.d(TAG, "logOnAny")
    }
}