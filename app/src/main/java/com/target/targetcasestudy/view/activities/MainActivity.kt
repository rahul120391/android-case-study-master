package com.target.targetcasestudy.view.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.target.targetcasestudy.BR
import com.target.targetcasestudy.Constants.CREDIT_CARD_VALIDATION
import com.target.targetcasestudy.Constants.DEAL_LIST_FRAGMENT
import com.target.targetcasestudy.R
import com.target.targetcasestudy.base.BaseActivity
import com.target.targetcasestudy.databinding.ActivityMainBinding
import com.target.targetcasestudy.view.fragments.DealListFragment
import com.target.targetcasestudy.view.fragments.PaymentDialogFragment
import com.target.targetcasestudy.viewModel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {

    @Inject
    override lateinit var viewModel: MainActivityViewModel

    override val layoutId: Int
        get() = R.layout.activity_main

    override val bindingVariable: Int
        get() = BR._all

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewDataBinding
        binding?.layoutContainer?.let { setInsets(it) }
        initializeToolBar()
        performFragmentTransaction(DealListFragment(),DEAL_LIST_FRAGMENT)
    }

    private fun initializeToolBar() {
        setSupportActionBar(binding?.toolBarMain)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }
        binding?.toolBarMain?.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
          R.id.credit_card -> {
            PaymentDialogFragment().show(supportFragmentManager, CREDIT_CARD_VALIDATION)
            true
          }
            else -> false
        }
    }

    fun setTitleText(text:String){
        binding?.txtTitle?.text = text
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount>1){
            supportFragmentManager.popBackStack()
            setTitleText(getString(R.string.deal_list))
        }
        else{
            finish()
        }
    }


}
