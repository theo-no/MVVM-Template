package com.moneyminions.mvvmtemplate.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moneyminions.mvvmtemplate.databinding.ItemRepoBinding
import com.moneyminions.mvvmtemplate.databinding.ItemUserBinding
import com.moneyminions.mvvmtemplate.dto.RepoResponse
import com.moneyminions.mvvmtemplate.dto.UserDto

private const val TAG = "차선호"
class RepoListAdapter: ListAdapter<RepoResponse, RepoListAdapter.RepoListHolder>(
    RepoListComparator
) {
    companion object RepoListComparator : DiffUtil.ItemCallback<RepoResponse>() {
        override fun areItemsTheSame(oldItem: RepoResponse, newItem: RepoResponse): Boolean {
            Log.d(TAG, "areItemsTheSame...")
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: RepoResponse, newItem: RepoResponse): Boolean {
            Log.d(TAG, "areContentsTheSame...")
            return oldItem.id  == newItem.id
        }
    }

    inner class RepoListHolder(binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.root){
        val textviewName = binding.textviewName
        val textviewUrl = binding.textviewUrl
        fun bindInfo(repo : RepoResponse){
            textviewName.text = repo.name
            textviewUrl.text = repo.url
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListHolder {
        Log.d(TAG, "onCreateViewHolder...")
        val binding = ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoListHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoListHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder....")
        holder.apply {
            bindInfo(getItem(position))
        }
    }
}