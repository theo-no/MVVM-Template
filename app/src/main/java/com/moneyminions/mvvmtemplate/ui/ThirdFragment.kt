package com.moneyminions.mvvmtemplate.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.moneyminions.mvvmtemplate.R
import com.moneyminions.mvvmtemplate.base.BaseFragment
import com.moneyminions.mvvmtemplate.databinding.FragmentThirdBinding
import com.moneyminions.mvvmtemplate.util.NavigationUtil

private const val TAG = "차선호"
class ThirdFragment : BaseFragment<FragmentThirdBinding>(
    FragmentThirdBinding::bind,
    R.layout.fragment_third
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NavigationUtil.hideBottomNavigation(mActivity.bottomNavigationView)
        initListener()
    }
    override fun initListener() {
        Log.d(TAG, "initListener...")
    }

}