package com.example.affrimationapp

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic (
    @StringRes val stringResourceId: Int,
    val courseNumber: Number,
    @DrawableRes val imageResource: Int
)