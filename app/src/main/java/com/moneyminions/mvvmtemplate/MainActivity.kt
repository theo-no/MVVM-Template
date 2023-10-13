package com.moneyminions.mvvmtemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.moneyminions.mvvmtemplate.base.BaseActivity
import com.moneyminions.mvvmtemplate.databinding.ActivityMainBinding
import com.moneyminions.mvvmtemplate.di.ApplicationClass.Companion.sharedPreferences

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {


    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavController()
    }

    private fun initNavController() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_graph)

        graph.setStartDestination(R.id.homeFragment)

        val navController = navHostFragment.navController
        navController.setGraph(graph, intent.extras)
    }
}