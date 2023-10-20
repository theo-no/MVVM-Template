package com.moneyminions.mvvmtemplate.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.moneyminions.mvvmtemplate.R
import com.moneyminions.mvvmtemplate.base.BaseFragment
import com.moneyminions.mvvmtemplate.databinding.FragmentGithubBinding
import com.moneyminions.mvvmtemplate.repository.GithubRepository
import com.moneyminions.mvvmtemplate.repository.GithubRepositoryImpl
import com.moneyminions.mvvmtemplate.ui.adapter.RepoListAdapter
import com.moneyminions.mvvmtemplate.viewmodel.GithubViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG = "차선호"
@AndroidEntryPoint
class GithubFragment : BaseFragment<FragmentGithubBinding>(
    FragmentGithubBinding::bind,
    R.layout.fragment_github
) {

    private val githubViewModel: GithubViewModel by viewModels()
    lateinit var repoListAdapter: RepoListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        initCollect()
    }

    private fun initView(){
        binding.apply {


            repoListAdapter = RepoListAdapter()
            recyclerviewSearch.apply {
                this.adapter = repoListAdapter
                layoutManager = LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false)
            }
        }


    }

    override fun initListener() {
        binding.apply {

            edittextSearch.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(value: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(value: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(value: Editable?) {
                    githubViewModel.setUserId(value.toString())
                }

            })

            buttonSearch.setOnClickListener {
                githubViewModel.getUserRepos()
            }
        }
    }

    private fun initCollect(){
        viewLifecycleOwner.lifecycleScope.launch {
            githubViewModel.repoList.collectLatest {
                repoListAdapter.submitList(it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            githubViewModel.error.collectLatest {
                mActivity.showToast(it.message.toString())
            }
        }
    }

}