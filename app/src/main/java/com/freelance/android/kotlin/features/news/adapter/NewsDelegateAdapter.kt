package com.freelance.android.kotlin.features.news.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.freelance.android.kotlin.R
import com.freelance.android.kotlin.R.id.*
import com.freelance.android.kotlin.commons.RedditNewsItem
import com.freelance.android.kotlin.commons.adapter.ViewType
import com.freelance.android.kotlin.commons.adapter.ViewTypeDelegateAdapter
import com.freelance.android.kotlin.commons.extensions.getFriendlyTime
import com.freelance.android.kotlin.commons.extensions.inflate
import com.freelance.android.kotlin.commons.extensions.loadImg
import kotlinx.android.synthetic.main.news_item.view.*

class NewsDelegateAdapter(val viewActions: onViewSelectedListener) : ViewTypeDelegateAdapter {

    interface onViewSelectedListener {
        fun onItemSelected(url: String?)
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return NewsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as NewsViewHolder
        holder.bind(item as RedditNewsItem)
    }

    inner class NewsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.news_item)) {

        fun bind(item: RedditNewsItem) = with(itemView) {
            //Picasso.with(itemView.context).load(item.thumbnail).into(img_thumbnail)
            img_thumbnail.loadImg(item.thumbnail)
            description.text = item.title
            author.text = item.author
            comments.text = "${item.numComments} comments"
            time.text = item.created.getFriendlyTime()

            super.itemView.setOnClickListener { viewActions.onItemSelected(item.url)}
        }
    }
}