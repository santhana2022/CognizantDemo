package com.cogni.demo

object ConfigData {
    const val minSdkVersion = 24
    const val targetSdkVersion = 31
    const val compileSdkVersion = 31

    // From Android plugin for Gradle 3.0.0 or higher,default version of the build tools that the plugin specifies.
    const val buildToolsVersion = "30.0.3"
    const val versionCode = 1
    const val versionName = "1.0"
}

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:7.0.3"

    object Kotlin {
        private const val version = "1.5.31"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
    }

    object AndroidX {
        const val appcompatVersion = "1.4.1"
        const val coreKtx = "androidx.core:core-ktx:1.7.0"
        const val appcompat = "androidx.appcompat:appcompat:$appcompatVersion"
        const val annotation = "androidx.annotation:annotation:1.2.0"

        // 2.1.2
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.1.2"

        object Lifecycle {
            private const val version = "2.4.0"
            const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
        }

        object Navigation {
            private const val nav_version = "2.5.0-alpha01"
            const val navigationFragment =
                "androidx.navigation:navigation-fragment-ktx:$nav_version"
            const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:$nav_version"
        }
    }

    object Google {
        const val gson = "com.google.code.gson:gson:2.8.2"
        const val material = "com.google.android.material:material:1.5.0"
    }

    object Others {
        const val okhttp = "com.squareup.okhttp3:okhttp:5.0.0-alpha.2"

        const val retrofitVersion = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:$retrofitVersion"

        const val coil = "io.coil-kt:coil:1.4.0"

        const val rxVersion = "2.0.1"
        const val rxAndroid = "io.reactivex.rxjava2:rxandroid:$rxVersion"
        const val rxJava = "io.reactivex.rxjava2:rxjava:$rxVersion"
    }
}