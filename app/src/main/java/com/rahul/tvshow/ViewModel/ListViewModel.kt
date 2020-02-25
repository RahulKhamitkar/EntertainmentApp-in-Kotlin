package com.rahul.tvshow.ViewModel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rahul.tvshow.Model.News
import com.rahul.tvshow.Model.NewsAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel: ViewModel() {
    private val newsAPIService = NewsAPIService()
    private val disposable = CompositeDisposable()


    val news = MutableLiveData<List<News>>()
    val loadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchFromRemote()
    }

    private fun fetchFromRemote() {
        loadError.value = false
        loading.value = true
        disposable.add(
            newsAPIService.getNewsData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<News>>(){

                    override fun onSuccess(newsData: List<News>) {
                        loading.value = false
                        news.value = newsData

                    }

                    override fun onError(e: Throwable) {
                        loading.value = false
                        loadError.value = true
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}