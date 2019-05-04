package com.nytimes.sample.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nytimes.sample.R
import com.nytimes.sample.data.model.Data
import kotlinx.android.extensions.LayoutContainer
import timber.log.Timber

/**
 */
class NewsAdapter(
    flightEvents: List<Data>,
    private val itemClick: (Data) -> Unit = {}
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    var flightEvents: List<Data> = flightEvents
        set(flightEvents) {
            field = flightEvents
            Timber.d("Adapter Called called ")
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return inflate.inflate(R.layout.news_item, parent, false)
                .run {
                    ItemViewHolder(this, itemClick)

                }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            (holder as ItemViewHolder).run {
                bind(flightEvents.get(position) as Data)
            }

    }



    override fun getItemCount() = flightEvents.size


    inner class ItemViewHolder(override val containerView: View, private val itemClick: (Data) -> Unit) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(flightEvent: Data) {
            with(flightEvent) {
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }







}