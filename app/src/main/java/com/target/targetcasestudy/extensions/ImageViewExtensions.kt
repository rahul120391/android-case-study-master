package com.target.targetcasestudy.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


fun ImageView.loadImage(context: Context, url:String){
    Glide.with(context).load(url).into(this)
}

fun ImageView.loadImageWithPlaceHolderAndErrorHolder(context: Context, url: String, placeHolder:Int, errorImage:Int){
    Glide.with(context).load(url)
        .placeholder(placeHolder)
        .error(errorImage)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                this@loadImageWithPlaceHolderAndErrorHolder.setImageResource(errorImage)
                return true
            }
            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                this@loadImageWithPlaceHolderAndErrorHolder.setImageDrawable(resource)
                return true
            }
        })
        .into(this)
}