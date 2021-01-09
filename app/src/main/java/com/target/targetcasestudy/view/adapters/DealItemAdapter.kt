package com.target.targetcasestudy.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.target.targetcasestudy.dataModel.Product
import com.target.targetcasestudy.databinding.DealListItemBinding
import com.target.targetcasestudy.viewModel.DealItemViewModel

class DealItemAdapter(
    private val lifeCycleOwner: LifecycleOwner,
    private val onRowClick: (Product) -> Unit
) : RecyclerView.Adapter<DealItemAdapter.ViewHolder>() {


    private val dealList = ArrayList<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DealListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dealList[position])
    }

    override fun getItemCount(): Int = dealList.size

    inner class ViewHolder(private val binding: DealListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var dealItemViewModel:DealItemViewModel?=null
        init {
            binding.root.setOnClickListener {
                if(adapterPosition>=0){
                    onRowClick(dealList[adapterPosition])
                }
            }
        }
        fun bind(deal:Product){
            dealItemViewModel = DealItemViewModel(deal)
            binding.apply {
                lifecycleOwner=lifeCycleOwner
                viewModel=dealItemViewModel
                executePendingBindings()
            }
        }
    }

    fun updateData(dealList:List<Product>){
        this.dealList.apply {
            clear()
            addAll(dealList)
            notifyDataSetChanged()
        }
    }

}