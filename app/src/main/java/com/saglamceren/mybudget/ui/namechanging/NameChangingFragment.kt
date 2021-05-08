package com.saglamceren.mybudget.ui.namechanging

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.saglamceren.mybudget.R
import com.saglamceren.mybudget.data.Gender
import com.saglamceren.mybudget.databinding.FragmentNameChangingBinding
import com.saglamceren.mybudget.utils.DialogUtils

class NameChangingFragment : Fragment() {

    private lateinit var binding: FragmentNameChangingBinding
    val viewModel: NameChangingViewModel by viewModels {
        NameChangingViewModelFactory(requireActivity().application)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentNameChangingBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpObserve()
        setUpClickListeners()

    }

    private fun setUpObserve() {
        viewModel.successLiveData.observe(viewLifecycleOwner, {
            findNavController().navigate(R.id.action_nameChangingFragment_to_mainFragment)
        })
    }

    private fun setUpClickListeners() {
        binding.materialButtonAddingSpendingAdding.setOnClickListener {
            val name = binding.textInputEditTextNameNameChanging.text
            val gender = getGenderId()

            when {
                name.isNullOrEmpty() -> {
                    DialogUtils.showWarning(context = requireContext(), message = "Lütfen isminizi girin!")
                }
                getGenderId() == -1 -> {
                    DialogUtils.showWarning(context = requireContext(), message = "Lütfen cinsiyetinizi seçin!")
                }
                else -> {
                    viewModel.setInfo(name.toString(), gender)
                }
            }
        }

        binding.toolBarNameChanging.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun getGenderId(): Int {
        return when {
            binding.radioButtonMale.isChecked -> {
                Gender.MALE.id
            }
            binding.radioButtonFemale.isChecked -> {
                Gender.FEMALE.id
            }
            binding.radioButtonOther.isChecked -> {
                Gender.OTHER.id
            }
            else -> -1
        }
    }

}