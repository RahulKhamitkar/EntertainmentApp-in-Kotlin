package com.rahul.tvshow.View


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahul.tvshow.Model.News

import com.rahul.tvshow.R
import com.rahul.tvshow.ViewModel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list_.*
import kotlinx.android.synthetic.main.fragment_list_.errorMessage
import kotlinx.android.synthetic.main.fragment_list_.loadingProgress
import kotlinx.android.synthetic.main.fragment_list_.newsRecyclerView
import kotlinx.android.synthetic.main.fragment_list_.refreshLayout
import kotlinx.android.synthetic.main.fragment_list_.view.*

/**
 * A simple [Fragment] subclass.
 */
class List_Fragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private var newsListAdapter = NewsListAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        newsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsListAdapter

        }

        refreshLayout.setOnRefreshListener {
            errorMessage.visibility = View.GONE
            newsRecyclerView.visibility = View.GONE
            loadingProgress.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false

        }

        observeViewModel()

    }

    private fun observeViewModel() {

        viewModel.news.observe(this, Observer {news->
            news?.let {
                newsRecyclerView.visibility = View.VISIBLE
                newsListAdapter.upgradeNewsList(news)
            }
        })

        viewModel.loadError.observe(this, Observer { isLoadError->
            isLoadError?.let {
                errorMessage.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(this, Observer { isLoading->
            isLoading?.let {
                loadingProgress.visibility = if (it) View.VISIBLE else View.GONE
                if (it){
                    errorMessage.visibility = View.GONE
                    newsRecyclerView.visibility = View.GONE
                }
            }
        })
    }


}
