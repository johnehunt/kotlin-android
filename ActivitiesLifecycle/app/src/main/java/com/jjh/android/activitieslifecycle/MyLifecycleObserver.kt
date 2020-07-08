package com.jjh.android.helloworld

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyLifecycleObserver : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun logOnCreate() {
        Log.d("MyLifecycleObserver", "logOnCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun logOnStart() {
        Log.d("MyLifecycleObserver", "logOnStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun logOnResume() {
        Log.d("MyLifecycleObserver", "logOnResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun logOnPause() {
        Log.d("MyLifecycleObserver", "logOnPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun logOnStop() {
        Log.d("MyLifecycleObserver", "logOnStop")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun logOnDestroy() {
        Log.d("MyLifecycleObserver", "logOnDestroy")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun logOnAny() {
        Log.d("MyLifecycleObserver", "logOnAny")
    }
}