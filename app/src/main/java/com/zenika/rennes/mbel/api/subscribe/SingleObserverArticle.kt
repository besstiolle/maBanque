package com.zenika.rennes.mbel.api.subscribe

import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class SingleObserverArticle<Article>: SingleObserver<Article> {
    /**
     * Provides the SingleObserver with the means of cancelling (disposing) the
     * connection (channel) with the Single in both
     * synchronous (from within `onSubscribe(Disposable)` itself) and asynchronous manner.
     * @param d the Disposable instance whose [Disposable.dispose] can
     * be called anytime to cancel the connection
     * @since 2.0
     */
    override fun onSubscribe(d: Disposable) {
        TODO("Not yet implemented")
    }

    /**
     * Notifies the SingleObserver with a single item and that the [Single] has finished sending
     * push-based notifications.
     *
     *
     * The [Single] will not call this method if it calls [.onError].
     *
     * @param t
     * the item emitted by the Single
     */
    override fun onSuccess(t: Article) {
        TODO("Not yet implemented")
    }

    /**
     * Notifies the SingleObserver that the [Single] has experienced an error condition.
     *
     *
     * If the [Single] calls this method, it will not thereafter call [.onSuccess].
     *
     * @param e
     * the exception encountered by the Single
     */
    override fun onError(e: Throwable) {
        TODO("Not yet implemented")
    }
}