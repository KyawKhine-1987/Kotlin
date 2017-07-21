package com.freelance.android.kotlin.features.news.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.freelance.android.kotlin.commons.adapter.ViewType
import com.freelance.android.kotlin.commons.adapter.ViewTypeDelegateAdapter
import com.freelance.android.kotlin.R
import com.freelance.android.kotlin.commons.extensions.inflate

class LoadingDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup) = LoadingViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
    }

    class LoadingViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.news_item_loading)) {
    }
}