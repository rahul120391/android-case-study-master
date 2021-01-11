package com.target.targetcasestudy.view.fragments

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.target.targetcasestudy.constants.Constants.DEAL_DATA
import com.target.targetcasestudy.constants.Constants.DEAL_DETAIL_FRAGMENT
import com.target.targetcasestudy.R
import com.target.targetcasestudy.base.BaseFragment
import com.target.targetcasestudy.dataModel.Product
import com.target.targetcasestudy.dataProvider.status.Status
import com.target.targetcasestudy.databinding.FragmentDealListBinding
import com.target.targetcasestudy.extensions.gone
import com.target.targetcasestudy.extensions.isGone
import com.target.targetcasestudy.extensions.visible
import com.target.targetcasestudy.view.activities.MainActivity
import com.target.targetcasestudy.view.adapters.DealItemAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
class DealListFragment : BaseFragment<FragmentDealListBinding>() {


    override val layoutId: Int
        get() = R.layout.fragment_deal_list
    private var binding: FragmentDealListBinding? = null
    private val adapter by lazy { DealItemAdapter(this, ::onRowItemClick) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding = viewDataBinding
        (activity as? MainActivity)?.setTitleText(getString(R.string.deal_list))
        setUpRecyclerView()
        setUpSwipeToRefresh()
        fetchList(isRefreshing=false)
    }

    private fun setUpRecyclerView() {
        binding?.rvDeals?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            val itemDecoration =
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            ContextCompat.getDrawable(requireContext(), R.drawable.deal_items_divider_bg)?.let {
                itemDecoration.setDrawable(
                    it
                )
            }
            adapter = this@DealListFragment.adapter
            addItemDecoration(itemDecoration)
        }
    }

    private fun setUpSwipeToRefresh() {
        binding?.swipeToRefresh?.setOnRefreshListener {
                  fetchList(isRefreshing = true)
        }
    }

    private fun fetchList(isRefreshing:Boolean) {
        lifecycleScope.launch {
            if(binding?.swipeToRefresh?.isRefreshing==false){
                showProgressLayout()
            }
            (activity as? MainActivity)?.viewModel?.getDealsData(isRefreshing)?.collectLatest {
                hideProgressLayout()
                binding?.swipeToRefresh?.isRefreshing=false
                when (it) {
                    Status.NoInternet -> {
                        binding?.apply {
                            if(rvDeals.isGone()){
                                txtError.apply {
                                    visible()
                                    text = getString(R.string.internet_connection_error)
                                }
                            }
                            else{
                                (activity as? MainActivity)?.showMessage(getString(R.string.no_internet_connection))
                            }
                        }
                    }
                    is Status.Error -> {
                        binding?.apply {
                            if(rvDeals.isGone()){
                                txtError.apply {
                                    visible()
                                    text = getString(R.string.something_went_wrong_error)
                                }
                            }
                            else{
                                (activity as? MainActivity)?.showMessage(getString(R.string.something_went_wrong_error))
                            }
                        }
                    }
                    is Status.Response -> {
                        it.dealList?.let { dealList ->
                            if (dealList.isNotEmpty()) {
                                binding?.apply {
                                    rvDeals.visible()
                                    txtError.gone()
                                    adapter.updateData(dealList)
                                }
                            } else {
                                binding?.apply {
                                    rvDeals.gone()
                                    txtError.apply {
                                        visible()
                                        text = getString(R.string.no_deal_found)
                                    }
                                }
                            }
                        } ?: kotlin.run {
                            binding?.apply {
                                rvDeals.gone()
                                txtError.apply {
                                    visible()
                                    text = getString(R.string.something_went_wrong_error)
                                }
                            }
                        }

                    }
                }
            }
        }
    }

    private fun showProgressLayout() {
        binding?.flDealsProgress?.visible()
    }

    private fun hideProgressLayout() {
        binding?.flDealsProgress?.gone()
    }

    private fun onRowItemClick(deal: Product) {
        val detailItemFragment = DealItemFragment()
        val bundle = Bundle()
        bundle.putParcelable(DEAL_DATA,deal)
        detailItemFragment.arguments = bundle
        (activity as? MainActivity)?.performFragmentTransaction(detailItemFragment,DEAL_DETAIL_FRAGMENT)
    }

}
