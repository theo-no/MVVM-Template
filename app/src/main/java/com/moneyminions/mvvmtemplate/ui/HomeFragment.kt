package com.moneyminions.mvvmtemplate.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.moneyminions.mvvmtemplate.R
import com.moneyminions.mvvmtemplate.base.BaseFragment
import com.moneyminions.mvvmtemplate.databinding.FragmentHomeBinding
import com.moneyminions.mvvmtemplate.util.NavigationUtil
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

private const val TAG = "차선호"
class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::bind,
    R.layout.fragment_home
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NavigationUtil.showBottomNavigation(mActivity.bottomNavigationView)
        initListener()
    }

    override fun initListener() {
        binding.apply {
            buttonMoveRecyclerviewFragment.setOnClickListener {
                findNavController().navigate(
                    R.id.action_homeFragment_to_thirdFragment
                )
            }
            buttonMovePreferenceFragment.setOnClickListener {
                findNavController().navigate(
                    R.id.action_homeFragment_to_preferenceFragment
                )
            }
            buttonMoveCameraFragment.setOnClickListener {
                findNavController().navigate(
                    R.id.action_homeFragment_to_cameraFragment
                )
            }
        }
    }

}