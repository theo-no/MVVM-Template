package com.moneyminions.mvvmtemplate.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.color.utilities.MaterialDynamicColors.onError
import com.moneyminions.mvvmtemplate.dto.RepoResponse
import com.moneyminions.mvvmtemplate.repository.GithubRepository
import com.moneyminions.mvvmtemplate.util.NetworkThrowable
import com.moneyminions.mvvmtemplate.util.onError
import com.moneyminions.mvvmtemplate.util.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "차선호"
@HiltViewModel
class GithubViewModel @Inject constructor(
    private val githubRepository: GithubRepository
): ViewModel() {

    private var _userId: String = ""
    fun setUserId(userId: String){
        _userId = userId
    }


    private var _repoList = MutableSharedFlow<List<RepoResponse>>()
    val repoList: SharedFlow<List<RepoResponse>>
        get() = _repoList.asSharedFlow()

    private val _error = MutableSharedFlow<NetworkThrowable>()
    var error = _error.asSharedFlow()

    fun getUserRepos(){
        viewModelScope.launch {
            kotlin.runCatching {
                githubRepository.getUserRepos(_userId)
            }.onSuccess {
                Log.d(TAG, "getUserRepos success : $it")
                _repoList.emit(it)
            }.onFailure {
                if (it is NetworkThrowable) {
                    _error.emit(it)
                } else {
                    _error.emit(NetworkThrowable.NetworkErrorThrowable())
                }
            }
        }
    }


}