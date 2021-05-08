package com.saglamceren.mybudget.ui.spendingadding

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.saglamceren.mybudget.data.Repository
import com.saglamceren.mybudget.data.local.model.Spending
import kotlinx.coroutines.launch

class SpendingAddingViewModel(app: Application) : ViewModel() {
    private val repository = Repository(app)
    val successLiveData = MutableLiveData<Unit>()

    fun addSpending(spending: Spending) {
        viewModelScope.launch {
            repository.addSpending(spending)
            successLiveData.value = Unit
        }
    }

}

class SpendingAddingViewModelFactory(private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SpendingAddingViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SpendingAddingViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
