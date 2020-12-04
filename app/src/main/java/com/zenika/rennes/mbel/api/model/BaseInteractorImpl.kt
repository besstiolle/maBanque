package com.zenika.rennes.mbel.api.model

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

open class BaseInteractorImpl: BaseInteractor, LifecycleObserver {

    var compositeDisposable: CompositeDisposable = CompositeDisposable()
    var listener: BaseListener? = null

    protected val retrofit = Retrofit.Builder()
        .baseUrl("https://mabanqueenligne.free.beeceptor.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()


    constructor(lifecycleOwner: LifecycleOwner) {
        lifecycleOwner.lifecycle.addObserver(this)
        compositeDisposable = CompositeDisposable()
    }

    // interactor listeners extend this
    interface BaseListener{}


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun cleanUp() {
        if (listener != null) {
            listener = null
        }
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}