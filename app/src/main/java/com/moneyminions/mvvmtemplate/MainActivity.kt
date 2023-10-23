package com.moneyminions.mvvmtemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.moneyminions.mvvmtemplate.base.BaseActivity
import com.moneyminions.mvvmtemplate.databinding.ActivityMainBinding
import com.moneyminions.mvvmtemplate.di.ApplicationClass.Companion.PERMISSION_LIST
import com.moneyminions.mvvmtemplate.util.checkAllPermission
import com.moneyminions.mvvmtemplate.util.checkOnePermission
import com.moneyminions.mvvmtemplate.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "차선호"
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermission()
        initNavController()
        setBottomNavigationView()
    }

    private fun requestPermission(){
        checkAllPermission(
            fragment = null,
            activity = this,
            getPermissionRejected = {it -> mainViewModel.getPermissionRejected(it)},
            setPermissionRejected = {it -> mainViewModel.setPermissionRejected(it)},
            getIsShowedPermissionDialog = {it -> mainViewModel.getIsShowedPermissionDialog(it+"show")},
            setIsShowedPermissionDialog = {it -> mainViewModel.setIsShowedPermissionDialog(it+"show")}
        )
    }

    private fun setBottomNavigationView(){
        bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.setupWithNavController(navController)
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