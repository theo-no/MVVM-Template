package com.moneyminions.mvvmtemplate.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.moneyminions.mvvmtemplate.R
import com.moneyminions.mvvmtemplate.base.BaseFragment
import com.moneyminions.mvvmtemplate.databinding.FragmentPreferenceBinding
import com.moneyminions.mvvmtemplate.databinding.FragmentRecyclerviewBinding
import com.moneyminions.mvvmtemplate.util.NavigationUtil
import com.moneyminions.mvvmtemplate.viewmodel.GithubViewModel
import com.moneyminions.mvvmtemplate.viewmodel.PrefenceViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "차선호"
@AndroidEntryPoint
class PreferenceFragment : BaseFragment<FragmentPreferenceBinding>(
    FragmentPreferenceBinding::bind,
    R.layout.fragment_preference
) {


    private val preferenceViewModel: PrefenceViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NavigationUtil.hideBottomNavigation(mActivity.bottomNavigationView)
        Log.d(TAG, "현재 preference의 access-token : ${preferenceViewModel.getAccessToken()}")
        initView()
        initListener()
    }

    private fun initView(){
        binding.apply {
            textviewResult.text = preferenceViewModel.getAccessToken()
        }
    }

    override fun initListener() {
        binding.apply {
            edittextAccesstoken.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(value: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(value: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(value: Editable?) {
                    preferenceViewModel.setAccessToken(value.toString())
                    Log.d(TAG, "현재 preference의 access-token : ${preferenceViewModel.getAccessToken()}")
                }
            })

            buttonAccesstoken.setOnClickListener {
                preferenceViewModel.putAccessToken()
                textviewResult.text = preferenceViewModel.getAccessToken()
            }
        }

    }
}