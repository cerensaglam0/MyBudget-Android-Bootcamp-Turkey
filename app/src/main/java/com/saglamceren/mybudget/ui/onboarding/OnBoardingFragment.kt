package com.saglamceren.mybudget.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.saglamceren.mybudget.R
import com.saglamceren.mybudget.databinding.FragmentOnBoardingBinding


class OnBoardingFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardingBinding
    private lateinit var adapter: OnBoardingAdapter
    private val viewModel: OnBoardingViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpObserve()
        setUpClickListeners()

    }

    private fun setUpObserve() {
        viewModel.getOnBoardingList().observe(viewLifecycleOwner, {
            setAdapter(it)
        })
    }

    private fun setUpClickListeners() {
        binding.buttonSkip.setOnClickListener {
            findNavController().navigate(R.id.nameChangingFragment)
        }
    }

    private fun setAdapter(list: ArrayList<OnBoardingItem>) {
        adapter = OnBoardingAdapter(list)
        binding.viewPagerOnboarding.adapter = OnBoardingAdapter(list)
    }

}