package com.jjh.android.rxandroiddemo

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private var textView: TextView? = null
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate()")
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)
    }

    override fun onStop() {
        Log.d(TAG, "onStop")
        super.onStop()
        disposable.apply {
            if (!isDisposed) {
                this.dispose()
            }
        }
    }

    fun onButtonClick(v: View?) {
        Log.d(TAG, "onButtonClick")
        Log.d(TAG, "setting up Observable")

        Observable.create<Int> { emitter ->
            SystemClock.sleep(1000) // simulate delay
            emitter.onNext(5)
            emitter.onComplete() }
                .observeOn(Schedulers.io())
                .doOnNext { Log.d(TAG, "next: $it") }
                .subscribe { textView?.text = it.toString() }

        Log.d(TAG, "Observable done")
    }

    private fun updateTheUserInterface(integer: Int) {
        textView?.text = "$integer"
    }
}