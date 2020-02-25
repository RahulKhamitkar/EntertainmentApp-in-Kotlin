package com.rahul.tvshow.Model

import com.google.gson.annotations.SerializedName

data class News(

    @SerializedName("icon")
    val icon: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("discription")
    val discrption: String?,

    @SerializedName("pic")
    val pic: String?,

    @SerializedName("date")
    val date: String?

)
