package com.cogni.demo.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<INTENT : ViewIntent, ACTION : ViewAction, STATE : ViewState> :
    ViewModel() {

    protected val mState = MutableLiveData<STATE>()
    val state: LiveData<STATE>
        get() {
            return mState
        }

    abstract fun handleAction(action: ACTION)
    abstract fun intentToAction(intent: INTENT): ACTION

    fun dispatchIntent(intent: INTENT) {
        handleAction(intentToAction(intent))
    }
}