package com.moneyminions.mvvmtemplate.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.moneyminions.mvvmtemplate.MainActivity

abstract class BaseBottomSheet<T : ViewBinding>(
    private val bind: (View) -> T,
    @LayoutRes layoutResId: Int
) : BottomSheetDialogFragment(layoutResId) {

    private var _binding: T? = null
    val binding get() = requireNotNull(_binding)
    private lateinit var _mActivity: MainActivity
    protected val mActivity get() = _mActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        _mActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bind(super.onCreateView(inflater, container, savedInstanceState)!!)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}