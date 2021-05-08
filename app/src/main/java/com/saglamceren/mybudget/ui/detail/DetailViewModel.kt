package com.saglamceren.mybudget.ui.detail

import android.app.Application
import androidx.lifecycle.*
import com.saglamceren.mybudget.R
import com.saglamceren.mybudget.data.Repository
import com.saglamceren.mybudget.data.SpendingCategory
import com.saglamceren.mybudget.data.local.model.Spending
import kotlinx.coroutines.launch

class DetailViewModel(
    private val state: SavedStateHandle,
    private val app: Application
) : ViewModel() {
    private val repository = Repository(app)

    var spendingLiveData = MutableLiveData<Spending>()
    val moneyTextLiveData = MutableLiveData<String>()
    val successLiveData = MutableLiveData<Unit>()
    val totalSpendingLiveData = MutableLiveData<Double>()

    init {
        spendingLiveData.value = state.get<Spending>("key_spending")
        moneyTextLiveData.value = state.get<String>("key_money_text")
        totalSpendingLiveData.value = state.get<Double>("key_total_spending")
    }

    val iconLiveData: LiveData<Int> = Transformations.map(spendingLiveData) {
        when (it.category) {
            SpendingCategory.RENT.id -> R.drawable.ic_house
            SpendingCategory.INVOICE.id -> R.drawable.ic_invoice
            SpendingCategory.OTHER.id -> R.drawable.ic_shopping_cart
            else -> R.drawable.ic_house
        }
    }

    val colorLiveData: LiveData<Int> = Transformations.map(spendingLiveData) {
        when (it.category) {
            SpendingCategory.RENT.id -> R.color.pink_800_20
            SpendingCategory.INVOICE.id -> R.color.deep_purple_800_20
            SpendingCategory.OTHER.id -> R.color.light_blue_20
            else -> R.color.pink_800_20
        }
    }

    val iconBackgroundLiveData: LiveData<Int> = Transformations.map(spendingLiveData) {
        when (it.category) {
            SpendingCategory.RENT.id -> R.color.pink_800
            SpendingCategory.INVOICE.id -> R.color.deep_purple_800
            SpendingCategory.OTHER.id -> R.color.light_blue
            else -> R.color.pink_800
        }
    }

    fun deleteSpending() {
        val spending = spendingLiveData.value ?: return

        viewModelScope.launch {
            repository.deleteSpending(spendingId = spending.id)
            successLiveData.value = Unit
        }
    }
}

class DetailViewModelFactory(
    owner: DetailFragment,
    private val app: Application
) : AbstractSavedStateViewModelFactory(owner, owner.arguments) {
    override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailViewModel(handle, app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}