package com.saglamceren.mybudget.ui.spendingadding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.saglamceren.mybudget.data.Currency
import com.saglamceren.mybudget.data.SpendingCategory
import com.saglamceren.mybudget.data.local.model.Spending
import com.saglamceren.mybudget.databinding.FragmentSpendingAddingBinding
import com.saglamceren.mybudget.utils.DialogUtils

class SpendingAddingFragment : Fragment() {
    private lateinit var binding: FragmentSpendingAddingBinding

    private val viewModel: SpendingAddingViewModel by viewModels {
        SpendingAddingViewModelFactory(requireActivity().application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSpendingAddingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setUpObserve()
        setUpClickListeners()

    }

    private fun setUpObserve() {
        viewModel.successLiveData.observe(viewLifecycleOwner, {
            findNavController().navigateUp()
        })
    }


    private fun setUpClickListeners() {
        binding.materialButtonAddingSpendingAdding.setOnClickListener {
            val description = binding.textInputEditTextDescSpendingAdding.text
            val money = binding.textInputEditTextSpendingSpendingAdding.text
            val categoryId = getCategoryId()
            val currencyId = getCurrencyId()

            when {
                description.isNullOrEmpty() -> {
                    DialogUtils.showWarning(context = requireContext(), message = "Lütfen açıklama girin!")
                }
                money.isNullOrEmpty() -> {
                    DialogUtils.showWarning(context = requireContext(), message = "Lütfen harcama girin!")
                }
                categoryId == -1 -> {
                    DialogUtils.showWarning(context = requireContext(), message = "Lütfen kategori seçin!")
                }
                currencyId == -1 -> {
                    DialogUtils.showWarning(context = requireContext(), message = "Lütfen para birimi seçin!")
                }
                else -> {
                    val spending = Spending(
                        description = description.toString(),
                        money = money.toString().toDouble(),
                        category = categoryId,
                        currency = currencyId
                    )

                    viewModel.addSpending(spending)
                }
            }
        }

        binding.toolBarSpendingAdding.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

    }


    private fun getCategoryId(): Int {
        return when {
            binding.radiobuttonBillSpendingAdding.isChecked -> {
                SpendingCategory.INVOICE.id
            }
            binding.radiobuttonRentSpendingAdding.isChecked -> {
                SpendingCategory.RENT.id
            }
            binding.radiobuttonOtherSpendingAdding.isChecked -> {
                SpendingCategory.OTHER.id
            }
            else -> -1
        }
    }

    private fun getCurrencyId(): Int {
        return when {
            binding.radiobuttonTLSpendingAdding.isChecked -> {
                Currency.TRY.id
            }
            binding.radiobuttonDolarSpendingAdding.isChecked -> {
                Currency.USD.id
            }
            binding.radiobuttonEuroSpendingAdding.isChecked -> {
                Currency.EUR.id
            }
            binding.radiobuttonSterlinSpendingAdding.isChecked -> {
                Currency.GBP.id
            }
            else -> -1
        }
    }


}
