package com.saglamceren.mybudget.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.saglamceren.mybudget.R
import com.saglamceren.mybudget.databinding.FragmentSplashBinding
import com.saglamceren.mybudget.extension.doOnAnimationEnd

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private val viewModel: SplashViewModel by viewModels {
        SplashViewModelFactory(requireActivity().application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserve()

        binding.animationViewSplash.doOnAnimationEnd {
            viewModel.openNextPage()
        }
    }

    private fun setUpObserve() {
        viewModel.openMainPage.observe(viewLifecycleOwner, {
            findNavController().navigate(R.id.action_splashFragment_to_mainFragment2)
        })

        viewModel.openEntryNamePage.observe(viewLifecycleOwner, {
            findNavController().navigate(R.id.action_splashFragment_to_onBoardingFragment)
        })
    }
}