package com.saglamceren.mybudget.ui.namechanging

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saglamceren.mybudget.data.Repository

class NameChangingViewModel(app: Application) : ViewModel() {
    private val repository = Repository(app)

    private val _successLiveData = MutableLiveData<Unit>()
    val successLiveData: LiveData<Unit>
        get() = _successLiveData

    fun setInfo(name: String, gender: Int) {
        repository.setInfo(name, gender)
        _successLiveData.value = Unit
    }
}

class NameChangingViewModelFactory(private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NameChangingViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NameChangingViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
