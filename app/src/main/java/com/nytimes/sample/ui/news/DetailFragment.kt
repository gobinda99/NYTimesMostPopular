package com.nytimes.sample.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.nytimes.sample.R
import com.nytimes.sample.data.model.News
import kotlinx.android.synthetic.main.frag_details.*

class DetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.intent?.getParcelableExtra<News>("news")?.apply {
            columnText.text = column;
            titleText.text = title
            byText.text = byline
            dateText.text = publishedDate
            abstractText.text = abstract
            media?.takeIf { it.isNotEmpty() }?.get(0)?.takeIf { "image".equals(it.type) }
                ?.mediaMetadata?.takeIf { it.isNotEmpty() && it.size > 2}?.get(1)?.url?.let {
                Glide.with(this@DetailFragment).load(it).into(image);
            }
        }

    }

}