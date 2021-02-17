package com.jjh.android.rxandroiddemo

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate()")
        setContentView(R.layout.activity_main)
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

    fun onButtonClick(v: View) {
        Log.d(TAG, "onButtonClick")
        Log.d(TAG, "setting up Observable")

        Observable.create<Int> { emitter ->
            SystemClock.sleep(1000) // simulate delay
            emitter.onNext(5)
            emitter.onComplete() }
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext { Log.d(TAG, "next: $it") }
                .subscribe { textView.text = it.toString() }

        Log.d(TAG, "Observable done")
    }

    fun onServiceButtonClick(v: View) {
        Log.d(TAG, "onServiceButtonClick")
        Log.d(TAG, "setting up Observable")

        Observable.create<Int> {
            val client = OkHttpClient()

            val request: Request = Request.Builder()
                .url("http://ergast.com/api/f1/2020/drivers.json")
                .get()
                .build()

            val len = try {
                val response: Response = client.newCall(request).execute()
                response.body?.string()?.length?:-1
            } catch (e: Exception) {
                Log.d(TAG, e.localizedMessage)
                -1
            }
            it.onNext(len)
            it.onComplete()
        }.subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.io())
            .doOnNext { Log.d(TAG, "next: $it") }
            .doOnError { Log.d(TAG, "findFriendById error $it") }
            .doOnComplete { Log.d(TAG, "findFriendById Completed") }
            .subscribe { textView.text = it.toString() }

    }

}