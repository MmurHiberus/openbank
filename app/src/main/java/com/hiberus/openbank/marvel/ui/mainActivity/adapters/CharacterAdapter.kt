package com.hiberus.openbank.marvel.ui.mainActivity.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hiberus.openbank.marvel.R
import com.hiberus.openbank.marvel.db.dto.CharacterWithItemsDto
import de.hdodenhof.circleimageview.CircleImageView
import java.util.stream.Collectors


//Not using databinding here because in adapters can cause performance issues.

class CharacterAdapter(
    private var dataStorage: List<CharacterWithItemsDto>?,
    clickListener: View.OnClickListener?
) : RecyclerView.Adapter<CharacterAdapter.MyViewHolder>() {
    private val TAG = "CharacterAdapter"
    private var screenData: List<CharacterWithItemsDto>?
    var clickListener: View.OnClickListener?
    private var filter: String? = null

    init {
        screenData = dataStorage
        this.clickListener = clickListener
    }

    fun setData(data: List<CharacterWithItemsDto>?) {
        if (data != null) {
            Log.d(TAG, "Data CharacterWithItemsDto adapter ->${data.size}")
        }
        dataStorage = data
        setFilter(filter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(
            parent.context
        )
        val v = inflater.inflate(R.layout.item_marvel_list, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val character = screenData!![position]
        holder.tvName.text = character.marvelCharacter!!.name
        holder.tvLinksQty.text = character.marvelCharacter!!.urlsQty.toString()
        Glide.with(holder.ivThumbnail)
            .load(character.marvelCharacter!!.thumbnail)
            .placeholder(R.drawable.marvel_unknown_hero)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.ivThumbnail)
        if (character.marvelCharacter!!.description.isNullOrEmpty()) {
            holder.ivHasDescription.setImageResource(R.drawable.ic_unchecked)
        } else {
            holder.ivHasDescription.setImageResource(R.drawable.ic_check)
        }
        holder.tvComicsValue.text =
            character.itemList?.filter { it.itemKind == "comics" }?.count().toString()
        holder.tvEventsValue.text =
            character.itemList?.filter { it.itemKind == "events" }?.count().toString()
        holder.tvSeriesValue.text =
            character.itemList?.filter { it.itemKind == "series" }?.count().toString()
        holder.tvStoriesValue.text =
            character.itemList?.filter { it.itemKind == "stories" }?.count().toString()
        if (clickListener != null) {
            holder.itemView.setOnClickListener(clickListener)
        }
        holder.itemView.tag = character
    }

    override fun getItemCount(): Int {
        return if (screenData == null) {
            0
        } else {
            screenData!!.size
        }
    }

    fun setFilter(filter: String?) {
        this.filter = filter
        Log.d(TAG, "Filter -> $filter")
        screenData = if (filter == null || filter.isEmpty()) {
            dataStorage
        } else {
            dataStorage?.stream()
                ?.filter { item: CharacterWithItemsDto ->
                    ((item.marvelCharacter!!.name != null && item.marvelCharacter!!.name!!.contains(
                        filter,
                        ignoreCase = true
                    )))

                }
                ?.collect(Collectors.toList())
        }
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tvCharName)
        var tvLinksQty: TextView = itemView.findViewById(R.id.tvLinksQTy)
        var tvComicsValue: TextView = itemView.findViewById(R.id.tvComicsValue)
        var tvSeriesValue: TextView = itemView.findViewById(R.id.tvSeriesValue)
        var tvEventsValue: TextView = itemView.findViewById(R.id.tvEventsValue)
        var tvStoriesValue: TextView = itemView.findViewById(R.id.tvStoriesValue)
        var ivThumbnail: CircleImageView = itemView.findViewById(R.id.circleImageView)
        var ivHasDescription: ImageView = itemView.findViewById(R.id.ivHasDescription)
    }


}