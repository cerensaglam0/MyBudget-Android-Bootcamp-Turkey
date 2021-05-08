package com.saglamceren.mybudget.ui.onboarding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saglamceren.mybudget.R

class OnBoardingViewModel : ViewModel() {

    fun getOnBoardingList(): MutableLiveData<ArrayList<OnBoardingItem>> {
        val onBoardingList = ArrayList<OnBoardingItem>()

        val onBoarding1 = OnBoardingItem(R.drawable.onboard_step_one, "Karşınızda harcama takip uygulaması", "Bu uygulama sayesinde günlük harcamalarınızı takip edebilirsiniz. ")
        val onBoarding2 = OnBoardingItem(
            R.drawable.onboard_step_three,
            "Gözden kaçan fazla harcamalara son",
            "Bu uygulama, gün içerisinde fazla yaptığınız harcamaları kontrol etmenizi sağlayarak, ay sonunda üzülmemenizi sağlayacak."
        )
        val onBoarding3 = OnBoardingItem(
            R.drawable.onboard_step_two,
            "Harcamanızı farklı para birimleriyle takip edin",
            "Harcamalarınızı TL, dolar, Euro, Sterlin para birimleriyle sürekli güncel kurlarıyla takip edebilirsiniz."
        )

        onBoardingList.add(onBoarding1)
        onBoardingList.add(onBoarding2)
        onBoardingList.add(onBoarding3)

        return MutableLiveData(onBoardingList)
    }


}