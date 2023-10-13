package com.moneyminions.mvvmtemplate.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moneyminions.mvvmtemplate.R
import com.moneyminions.mvvmtemplate.base.BaseFragment
import com.moneyminions.mvvmtemplate.databinding.FragmentSecondBinding

class SecondFragment : BaseFragment<FragmentSecondBinding>(
    FragmentSecondBinding::bind,
    R.layout.fragment_second
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    override fun initListener() {
        TODO("Not yet implemented")
    }

}