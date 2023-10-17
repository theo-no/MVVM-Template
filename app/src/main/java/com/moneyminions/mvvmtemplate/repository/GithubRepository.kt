package com.moneyminions.mvvmtemplate.repository

import com.moneyminions.mvvmtemplate.dto.RepoInfoResponse
import com.moneyminions.mvvmtemplate.dto.RepoResponse
import com.moneyminions.mvvmtemplate.util.NetworkResult

interface GithubRepository {
    suspend fun getUserRepos(user: String): NetworkResult<List<RepoResponse>>

    suspend fun getRepoInfo(user: String, repo: String): NetworkResult<RepoInfoResponse>
}