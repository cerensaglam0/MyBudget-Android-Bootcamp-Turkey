package com.saglamceren.mybudget.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.saglamceren.mybudget.R
import com.saglamceren.mybudget.data.Currency
import com.saglamceren.mybudget.data.local.model.Spending
import com.saglamceren.mybudget.databinding.FragmentMainBinding

class MainFragment : Fragment(), SpendingAdapterCallback {

    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: SpendingAdapter

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(requireActivity().application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpObserve()
        setUpClickListeners()

        viewModel.getRateByCurrency()
    }

    private fun setUpObserve() {
        viewModel.getInfoText().observe(viewLifecycleOwner, {
            binding.textViewName.text = it
        })

        viewModel.spendingLiveData.observe(viewLifecycleOwner, {
            adapter = SpendingAdapter(
                itemList = it,
                currency = viewModel.selectedCurrency,
                callback = this
            )
            binding.recyclerViewSpending.adapter = adapter
        })

    }

    private fun setUpClickListeners() {
        binding.buttonAdd.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_spendingAddingFragment)
        }
        binding.textViewName.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_nameChangingFragment)
        }

        binding.radioButtonTL.setOnClickListener {
            viewModel.getRateByCurrency(Currency.TRY)
        }

        binding.radioButtonDollar.setOnClickListener {
            viewModel.getRateByCurrency(Currency.USD)
        }

        binding.radioButtonEuro.setOnClickListener {
            viewModel.getRateByCurrency(Currency.EUR)
        }

        binding.radioButtonSterlin.setOnClickListener {
            viewModel.getRateByCurrency(Currency.GBP)
        }
    }

    override fun onSpendingItemClick(spending: Spending, moneyText: String) {
        val args = bundleOf(
            "key_spending" to spending,
            "key_money_text" to moneyText,
            "key_total_spending" to viewModel.totalSpending
        )
        findNavController().navigate(R.id.action_mainFragment_to_detailFragment, args)
    }
}