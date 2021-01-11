package com.target.targetcasestudy.view.fragments


import android.os.Bundle
import android.view.View
import com.target.targetcasestudy.constants.Constants.DEAL_DATA
import com.target.targetcasestudy.R
import com.target.targetcasestudy.base.BaseFragment
import com.target.targetcasestudy.dataModel.Product
import com.target.targetcasestudy.databinding.FragmentDealItemBinding
import com.target.targetcasestudy.view.activities.MainActivity
import com.target.targetcasestudy.viewModel.DealItemDetailDataBind
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
class DealItemFragment : BaseFragment<FragmentDealItemBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_deal_item

    private var binding: FragmentDealItemBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = viewDataBinding
        (activity as? MainActivity)?.setTitleText(getString(R.string.deal_item))
        arguments?.let {
            val product = it.getParcelable<Product>(DEAL_DATA)
            product?.let { it1 -> binding?.let { it2 -> DealItemDetailDataBind(it1, it2) } }
        }
    }



}
