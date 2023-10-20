package com.moneyminions.mvvmtemplate.util

import android.content.Context
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.moneyminions.mvvmtemplate.MainActivity
import com.moneyminions.mvvmtemplate.R

object NavigationUtil{
    fun hideBottomNavigation(bottomNavigationView: BottomNavigationView){
        bottomNavigationView.visibility = View.GONE
    }
    fun showBottomNavigation(bottomNavigationView: BottomNavigationView){
        bottomNavigationView.visibility = View.VISIBLE
    }
}