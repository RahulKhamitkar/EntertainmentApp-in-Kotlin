package com.rahul.tvshow.Model

import io.reactivex.Single
import retrofit2.http.GET

interface NewsAPI {

    @GET(".json")
    fun getNewsData(): Single<List<News>>

}