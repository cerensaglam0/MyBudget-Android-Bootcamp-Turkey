package com.saglamceren.mybudget.ui.splash

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saglamceren.mybudget.data.Repository

class SplashViewModel(app: Application) : ViewModel() {
    private val repository = Repository(app)

    val openMainPage = MutableLiveData<Unit>()
    val openEntryNamePage = MutableLiveData<Unit>()

    fun openNextPage() {
        if (repository.isFirstEntry()) {
            openEntryNamePage.value = Unit
        } else {
            openMainPage.value = Unit
        }
    }
}

class SplashViewModelFactory(private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SplashViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
