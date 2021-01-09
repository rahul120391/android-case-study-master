package com.target.targetcasestudy.viewModel

import android.graphics.Paint
import androidx.core.content.ContextCompat
import com.target.targetcasestudy.R
import com.target.targetcasestudy.dataModel.Product
import com.target.targetcasestudy.databinding.FragmentDealItemBinding
import com.target.targetcasestudy.extensions.gone
import com.target.targetcasestudy.extensions.loadImageWithPlaceHolderAndErrorHolder

class DealItemDetailDataBind(product: Product,binding: FragmentDealItemBinding) {
    init {
        binding.apply {
            imgDeal.loadImageWithPlaceHolderAndErrorHolder(imgDeal.context,product.image_url?:"", R.drawable.ic_launcher_background,R.drawable.ic_launcher_background)
            product.regular_price?.let {
                txtRegularPriceValue.text =it.display_string?:""
            }?: kotlin.run {
                txtRegularPrice.gone()
            }
            product.sale_price?.let {
                txtSalePrice.text = it.display_string?:""
                 if(txtSalePrice.text != ""){
                     txtRegularPriceValue.apply {
                         paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                     }
                }
            }
            txtDealTitle.text = product.title
            txtDescription.text = product.description
        }
    }

}