package com.target.targetcasestudy.viewModel

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.google.android.material.imageview.ShapeableImageView
import com.target.targetcasestudy.R
import com.target.targetcasestudy.dataModel.Product
import com.target.targetcasestudy.extensions.loadImageWithPlaceHolderAndErrorHolder

class DealItemViewModel(product: Product) {

    val title = MutableLiveData(product.title)
    val price = MutableLiveData(product.regular_price?.display_string)
    val aisle = MutableLiveData(product.aisle)
    val imageUrl = MutableLiveData(product.image_url)
    companion object{
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(imageView: AppCompatImageView, url:String?){
            url?.let {
                if(it.isNotBlank()){
                    imageView.loadImageWithPlaceHolderAndErrorHolder(imageView.context,it,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background)
                }
                else{
                    imageView.setImageResource(R.drawable.ic_launcher_background)
                }
            }?: kotlin.run {
                imageView.setImageResource(R.drawable.ic_launcher_background)
            }

        }


    }
}