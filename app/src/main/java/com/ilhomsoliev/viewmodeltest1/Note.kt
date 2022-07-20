package com.ilhomsoliev.viewmodeltest1

import androidx.compose.ui.graphics.Color

data class Note(
    val id:Int,
    val title:String,
    val content:String,
    val color: Color,
    val date:Long
)