package com.target.targetcasestudy.base

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import com.target.targetcasestudy.R
import com.target.targetcasestudy.view.activities.MainActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
abstract class BaseActivity<T : ViewDataBinding, V : ViewModel> : AppCompatActivity() {

    @get:LayoutRes
    abstract val layoutId: Int
    var viewDataBinding: T? = null
    private var baseViewModel: V? = null

    abstract val viewModel: V

    abstract val bindingVariable: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        setUpTransparentStatusBar()
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        this.baseViewModel = if (baseViewModel == null) viewModel else baseViewModel
        viewDataBinding?.setVariable(bindingVariable, baseViewModel)
        viewDataBinding?.executePendingBindings()
    }

    private fun setUpTransparentStatusBar() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            window.apply {
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                WindowCompat.setDecorFitsSystemWindows(this, false)
                statusBarColor = Color.TRANSPARENT
            }
        } else {
            window?.apply {
                setDecorFitsSystemWindows(false)
                statusBarColor = Color.TRANSPARENT
            }
        }
    }

    fun setInsets(container: View) {
        ViewCompat.setOnApplyWindowInsetsListener(container) { v, insets ->
            val systemBarMask = WindowInsetsCompat.Type.systemBars()
            insets.getInsets(systemBarMask).run {
                insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
                WindowInsetsCompat.Builder().setInsets(
                    systemBarMask,
                    Insets.of(left, 0, right, bottom)
                ).build().apply {
                    ViewCompat.onApplyWindowInsets(v, this)
                }
            }
        }
    }

    fun performFragmentTransaction(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, fragment, tag)
            .addToBackStack(tag)
            .commit()
    }


    fun showMessage(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()
    }

}