package com.moneyminions.mvvmtemplate.repository

import com.moneyminions.mvvmtemplate.dto.RepoInfoResponse
import com.moneyminions.mvvmtemplate.dto.RepoResponse
import com.moneyminions.mvvmtemplate.service.BusinessService
import com.moneyminions.mvvmtemplate.util.NetworkResult
import com.moneyminions.mvvmtemplate.util.handleApi
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val businessService: BusinessService
): GithubRepository {
    override suspend fun getUserRepos(user: String): List<RepoResponse> {
        return handleApi { businessService.getUserRepos(user) }
    }

    override suspend fun getRepoInfo(user: String, repo: String): RepoInfoResponse {
        return handleApi { businessService.getRepoInfo(user, repo) }
    }
}