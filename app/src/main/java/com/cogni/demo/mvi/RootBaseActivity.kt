package com.cogni.demo.mvi

import androidx.appcompat.app.AppCompatActivity
import com.cogni.demo.di.AppRouter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
open class RootBaseActivity : AppCompatActivity() {

    @Inject
    lateinit var appRouter: AppRouter

//    @Inject
//    lateinit var viewModelFactory: DaggerViewModelFactory
}