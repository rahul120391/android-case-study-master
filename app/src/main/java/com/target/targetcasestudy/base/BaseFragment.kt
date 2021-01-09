package com.target.targetcasestudy.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T:ViewDataBinding>: Fragment() {


    var viewDataBinding:T?=null
    @get:LayoutRes
    abstract val layoutId:Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(layoutInflater,layoutId,container,false)
        return viewDataBinding?.root
    }


}