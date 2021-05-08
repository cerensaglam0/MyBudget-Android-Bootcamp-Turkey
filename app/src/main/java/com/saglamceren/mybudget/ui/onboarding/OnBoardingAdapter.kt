package com.saglamceren.mybudget.ui.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.saglamceren.mybudget.databinding.ItemOnboardingBinding

class OnBoardingAdapter(private val itemsList: ArrayList<OnBoardingItem>) : PagerAdapter() {

    override fun getCount(): Int {
        return itemsList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = LayoutInflater.from(container.context)
        val binding = ItemOnboardingBinding.inflate(
            layoutInflater,
            container,
            false
        )

        val currentItem = itemsList[position]

        binding.onBoarding = currentItem
        //binding.imageViewOnBoarding.setImageResource(currentItem.image)

        container.addView(binding.root)

        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}