package com.zenika.rennes.mbel.api.subscribe

import io.reactivex.disposables.Disposable


class CompositeDisposable: Disposable  {
    /**
     * Dispose the resource, the operation should be idempotent.
     */
    override fun dispose() {
        TODO("Not yet implemented")
    }

    /**
     * Returns true if this resource has been disposed.
     * @return true if this resource has been disposed
     */
    override fun isDisposed(): Boolean {
        TODO("Not yet implemented")
    }

}