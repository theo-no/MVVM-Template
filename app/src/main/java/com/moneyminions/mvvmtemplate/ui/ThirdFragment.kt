package com.moneyminions.mvvmtemplate.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.moneyminions.mvvmtemplate.R
import com.moneyminions.mvvmtemplate.base.BaseFragment
import com.moneyminions.mvvmtemplate.databinding.FragmentThirdBinding
import com.moneyminions.mvvmtemplate.dto.UserDto
import com.moneyminions.mvvmtemplate.util.NavigationUtil

private const val TAG = "차선호"
class ThirdFragment : BaseFragment<FragmentThirdBinding>(
    FragmentThirdBinding::bind,
    R.layout.fragment_third
) {
    val userList = listOf<UserDto>(
        UserDto(0,"아이유",27,"https://cdnimg.melon.co.kr/cm2/artistcrop/images/002/61/143/261143_20210325180240_org.jpg?61e575e8653e5920470a38d1482d7312/melon/optimize/90"),
        UserDto(1,"아이유",27,"https://dimg.donga.com/wps/NEWS/IMAGE/2023/01/02/117259889.2.jpg"),
        UserDto(2,"박보영",30,"https://cdn.sisamagazine.co.kr/news/photo/202305/487655_495672_1532.jpg"),
        UserDto(3,"아이유",27,"https://cdnimg.melon.co.kr/cm2/artistcrop/images/002/61/143/261143_20210325180240_org.jpg?61e575e8653e5920470a38d1482d7312/melon/optimize/90"),
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NavigationUtil.hideBottomNavigation(mActivity.bottomNavigationView)
        initView()
        initListener()
    }
    override fun initListener() {
        Log.d(TAG, "initListener...")
    }

    private fun initView(){
        val exampleListAdpater = ExampleListAdapter()
        exampleListAdpater.submitList(userList)
        binding.exampleRecyclerview.apply {
            this.adapter = exampleListAdpater.apply {
            }
            layoutManager = LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false)
        }
    }

}