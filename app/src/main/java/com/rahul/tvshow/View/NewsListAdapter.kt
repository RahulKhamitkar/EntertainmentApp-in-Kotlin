package com.rahul.tvshow.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahul.tvshow.Model.News
import com.rahul.tvshow.R
import com.rahul.tvshow.Utils.getProgressDrawable
import com.rahul.tvshow.Utils.loadImage
import kotlinx.android.synthetic.main.card_view_news.view.*

class NewsListAdapter(val newsList: ArrayList<News>) : RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>(){

    fun upgradeNewsList(newNewsList: List<News>){
        newsList.clear()
        newsList.addAll(newNewsList)
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_view_news,parent,false)
        return NewsViewHolder(view)
    }

    override fun getItemCount() = newsList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.view.iTitle.text = newsList[position].title
        holder.view.iDate.text = newsList[position].date
        holder.view.iDiscription.text = newsList[position].discrption
        holder.view.iIcon.loadImage(newsList[position].icon,
            getProgressDrawable(holder.view.iIcon.context))
        holder.view.iPic.loadImage(newsList[position].pic, getProgressDrawable(holder.view.iPic.context))
    }
    class NewsViewHolder(var view: View) : RecyclerView.ViewHolder(view)


}