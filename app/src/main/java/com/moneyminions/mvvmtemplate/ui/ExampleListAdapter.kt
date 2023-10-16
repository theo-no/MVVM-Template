package com.moneyminions.mvvmtemplate.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moneyminions.mvvmtemplate.databinding.ItemUserBinding
import com.moneyminions.mvvmtemplate.dto.UserDto

private const val TAG = "차선호"
class ExampleListAdapter: ListAdapter<UserDto, ExampleListAdapter.ExampleListHolder>(
    ExampleListComparator
) {
    companion object ExampleListComparator : DiffUtil.ItemCallback<UserDto>() {
        override fun areItemsTheSame(oldItem: UserDto, newItem: UserDto): Boolean {
            Log.d(TAG, "areItemsTheSame...")
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: UserDto, newItem: UserDto): Boolean {
            Log.d(TAG, "areContentsTheSame...")
            return oldItem.userId  == newItem.userId
        }
    }

    inner class ExampleListHolder(binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root){
        val profileImage = binding.imgProfile
        val nameTextView = binding.textviewName
        val ageTextView = binding.textviewAge
        fun bindInfo(user : UserDto){
            Log.d(TAG, "bindInfo profileImg : ${user.profileImg}")
            Glide.with(profileImage)
                .load(user.profileImg)
                .into(profileImage)
            nameTextView.text = user.name
            ageTextView.text = user.age.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleListHolder {
        Log.d(TAG, "onCreateViewHolder...")
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExampleListHolder(binding)
    }

    override fun onBindViewHolder(holder: ExampleListHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder....")
        holder.apply {
            bindInfo(getItem(position))
        }
    }
}