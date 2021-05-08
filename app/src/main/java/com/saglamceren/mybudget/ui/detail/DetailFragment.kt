package com.saglamceren.mybudget.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.saglamceren.mybudget.R

import com.saglamceren.mybudget.databinding.FragmentDetailBinding
import com.saglamceren.mybudget.ui.MainActivity

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModels {
        DetailViewModelFactory(this, requireActivity().application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpObserve()
        setUpClickListeners()
    }

    private fun setUpObserve() {
        viewModel.colorLiveData.observe(viewLifecycleOwner) {
            (activity as MainActivity).updateStatusBarColor(it)
        }

        viewModel.successLiveData.observe(viewLifecycleOwner) {
            findNavController().navigateUp()
        }
    }

    private fun setUpClickListeners() {
        binding.materialButtonDelete.setOnClickListener {
            viewModel.deleteSpending()
        }
    }

    override fun onDestroy() {
        (activity as MainActivity).updateStatusBarColor(R.color.white)
        super.onDestroy()
    }


}