package com.saglamceren.mybudget.ui.main

import android.app.Application
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saglamceren.mybudget.data.Currency
import com.saglamceren.mybudget.data.Repository
import com.saglamceren.mybudget.data.SpendingCategory
import com.saglamceren.mybudget.data.local.model.Spending
import com.saglamceren.mybudget.extension.toMoney
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(app: Application) : ViewModel() {
    private val repository = Repository(app)
    private val disposable = CompositeDisposable()

    var selectedCurrency: Currency = Currency.TRY
    var totalSpending: Double = 0.0

    private val _spendingListLiveData = MutableLiveData<List<Spending>>()
    val spendingLiveData: LiveData<List<Spending>>
        get() = _spendingListLiveData

    val totalText = MutableLiveData<String>()
    val invoiceText = MutableLiveData<String>()
    val rentText = MutableLiveData<String>()
    val otherText = MutableLiveData<String>()
    val progressBarVisibility = MutableLiveData<Int>()


    fun getRateByCurrency(currency: Currency = selectedCurrency) {
        progressBarVisibility.value = View.VISIBLE
        selectedCurrency = currency
        disposable.add(
            repository.convertCurrency(currency)
                .subscribe(
                    {
                        progressBarVisibility.value = View.GONE
                        _spendingListLiveData.value = it
                        publishSpendingTexts(currency, it)
                    },
                    {
                        progressBarVisibility.value = View.GONE
                        val spendingList = repository.getSpendingList(currency)
                        _spendingListLiveData.value = spendingList
                        publishSpendingTexts(currency, spendingList)
                    }
                )
        )
    }


    fun getInfoText(): LiveData<String> {
        return MutableLiveData(repository.getInfoText())
    }

    private fun publishSpendingTexts(currency: Currency, spendingList: List<Spending>) {
        totalSpending = spendingList.map {
            it.money
        }.sum()
        totalText.value = totalSpending.toMoney(currency)

        invoiceText.value = spendingList.filter {
            it.category == SpendingCategory.INVOICE.id
        }.map {
            it.money
        }.sum().toMoney(currency)

        rentText.value = spendingList.filter {
            it.category == SpendingCategory.RENT.id
        }.map {
            it.money
        }.sum().toMoney(currency)

        otherText.value = spendingList.filter {
            it.category == SpendingCategory.OTHER.id
        }.map {
            it.money
        }.sum().toMoney(currency)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}

class MainViewModelFactory(private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
