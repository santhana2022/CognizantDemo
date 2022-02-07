package com.cogni.demo.core

import android.content.Context
import androidx.fragment.app.Fragment

abstract class BaseFragment<INTENT : ViewIntent, ACTION : ViewAction, STATE : ViewState,
        VM : BaseViewModel<INTENT, ACTION, STATE>> :
    Fragment() {

    private lateinit var viewState: STATE
    val mState get() = viewState

    private val viewModel: VM by lazy {
        getScreenViewModel()
    }

    abstract fun getScreenViewModel(): VM

    fun dispatchIntent(intent: INTENT) {
        viewModel.dispatchIntent(intent)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initDATA()
        initEVENT()
        viewModel.state.observe(this) {
            viewState = it
            render(it)
        }
    }

    abstract fun render(state: STATE)
//    abstract fun initUI()
    abstract fun initDATA()
    abstract fun initEVENT()
}