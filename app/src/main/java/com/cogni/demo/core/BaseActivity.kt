package com.cogni.demo.core

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<INTENT : ViewIntent, ACTION : ViewAction, STATE : ViewState,
        VM : BaseViewModel<INTENT, ACTION, STATE>> :
    AppCompatActivity() {

    private val viewModel: VM by lazy {
        getScreenViewModel()
    }

    abstract fun getScreenViewModel(): VM

    fun dispatchIntent(intent: INTENT) {
        viewModel.dispatchIntent(intent)
    }

    abstract fun initUI()
    abstract fun initDATA()
    abstract fun initEVENT()
}