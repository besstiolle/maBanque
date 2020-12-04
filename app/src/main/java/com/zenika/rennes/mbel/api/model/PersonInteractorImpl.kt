package com.zenika.rennes.mbel.api.model

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.zenika.rennes.mbel.api.service.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PersonInteractorImpl(lifecycleOwner: LifecycleOwner): BaseInteractorImpl(lifecycleOwner), PersonInteractor {

    private val apiService: ApiService = retrofit.create(ApiService::class.java)

    interface PersonListener : BaseListener {
        fun onDataReady(article: Article?)
        fun onError()
    }

    override fun getArticle() {
        apiService.getArticle()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { articleData -> (listener as PersonListener).onDataReady(articleData) },
                { throwable -> (listener as PersonListener).onError() }, {},
                { disposable -> compositeDisposable?.add(disposable) })
    }
}