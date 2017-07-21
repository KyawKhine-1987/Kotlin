package com.freelance.android.kotlin.features.news

import com.freelance.android.kotlin.commons.extensions.inflate
import kotlinx.android.synthetic.main.news_fragment.*

class NewsFragment : com.freelance.android.kotlin.commons.RxBaseFragment(), com.freelance.android.kotlin.features.news.adapter.NewsDelegateAdapter.onViewSelectedListener {
    override fun onItemSelected(url: String?) {
        if (url.isNullOrEmpty()) {
            android.support.design.widget.Snackbar.make(news_list, "No URL assigned to this news", android.support.design.widget.Snackbar.LENGTH_LONG).show()
        } else {
            val intent = android.content.Intent(android.content.Intent.ACTION_VIEW)
            intent.data = android.net.Uri.parse(url)
            startActivity(intent)
        }
    }

    companion object {
        private val KEY_REDDIT_NEWS = "redditNews"
    }

    @javax.inject.Inject lateinit var newsManager: NewsManager
    private var redditNews: com.freelance.android.kotlin.commons.RedditNews? = null

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        com.freelance.android.kotlin.KotlinApp.Companion.newsComponent.inject(this)
    }

    override fun onCreateView(inflater: android.view.LayoutInflater, container: android.view.ViewGroup?, savedInstanceState: android.os.Bundle?): android.view.View? {
        return container?.inflate(com.freelance.android.kotlin.R.layout.news_fragment)
    }

    override fun onActivityCreated(savedInstanceState: android.os.Bundle?) {
        super.onActivityCreated(savedInstanceState)

        news_list.apply {
            setHasFixedSize(true)
            val linearLayout = android.support.v7.widget.LinearLayoutManager(context)
            layoutManager = linearLayout
            clearOnScrollListeners()
            addOnScrollListener(com.freelance.android.kotlin.commons.InfiniteScrollListener({ requestNews() }, linearLayout))
        }

        initAdapter()

        if (savedInstanceState != null && savedInstanceState.containsKey(com.freelance.android.kotlin.features.news.NewsFragment.Companion.KEY_REDDIT_NEWS)) {
            redditNews = savedInstanceState.get(com.freelance.android.kotlin.features.news.NewsFragment.Companion.KEY_REDDIT_NEWS) as com.freelance.android.kotlin.commons.RedditNews
            (news_list.adapter as com.freelance.android.kotlin.features.news.adapter.NewsAdapter).clearAndAddNews(redditNews!!.news)
        } else {
            requestNews()
        }
    }

    override fun onSaveInstanceState(outState: android.os.Bundle) {
        super.onSaveInstanceState(outState)
        val news = (news_list.adapter as com.freelance.android.kotlin.features.news.adapter.NewsAdapter).getNews()
        if (redditNews != null && news.isNotEmpty()) {
            outState.putParcelable(com.freelance.android.kotlin.features.news.NewsFragment.Companion.KEY_REDDIT_NEWS, redditNews?.copy(news = news))
        }
    }

    private fun requestNews() {
        /**
         * first time will send empty string for 'after' parameter.
         * Next time we will have redditNews set with the next page to
         * navigate with the 'after' param.
         */
        val subscription = newsManager.getNews(redditNews?.after ?: "")
                .subscribeOn(rx.schedulers.Schedulers.io())
                .observeOn(rx.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe (
                        { retrievedNews ->
                            redditNews = retrievedNews
                            (news_list.adapter as com.freelance.android.kotlin.features.news.adapter.NewsAdapter).addNews(retrievedNews.news)
                        },
                        { e ->
                            android.support.design.widget.Snackbar.make(news_list, e.message ?: "", android.support.design.widget.Snackbar.LENGTH_LONG).show()
                        }
                )
        subscriptions.add(subscription)
    }

    private fun initAdapter() {
        if (news_list.adapter == null) {
            news_list.adapter = com.freelance.android.kotlin.features.news.adapter.NewsAdapter(this)
        }
    }
}

