package com.moneyminions.mvvmtemplate.service

import com.moneyminions.mvvmtemplate.dto.RepoInfoResponse
import com.moneyminions.mvvmtemplate.dto.RepoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BusinessService {
    @GET("users/{user}/repos")
    suspend fun getUserRepos(@Path("user") user:String): List<RepoResponse>

    @GET("repos/{user}/{repo}")
    suspend fun getRepoInfo(@Path("user") user: String, @Path("repo") repo: String): RepoInfoResponse
}