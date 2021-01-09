package com.target.targetcasestudy.extensions

import android.view.View


fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.gone(){
    this.visibility = View.GONE
}

fun View.invisible(){
    this.visibility = View.INVISIBLE
}

fun View.isVisible():Boolean{
    return this.visibility== View.VISIBLE
}

fun View.isGone():Boolean{
    return this.visibility== View.GONE
}

fun View.isInvisible():Boolean{
    return this.visibility == View.INVISIBLE
}