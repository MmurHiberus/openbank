package com.hiberus.openbank.marvel.ui.mainActivity.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hiberus.openbank.marvel.R
import com.hiberus.openbank.marvel.db.entities.CharacterItem
import java.util.*
import java.util.stream.Collectors


//Not using databinding here because in adapters can cause performance issues.

class CharacterItemAdapter(
    private var dataStorage: List<CharacterItem>?
) : RecyclerView.Adapter<CharacterItemAdapter.MyViewHolder>() {
    private val TAG = "CharacterItemAdapter"
    private var screenData: List<CharacterItem>? = ArrayList()
    private var filter: MutableList<String> = ArrayList()
    var characterItemAdapterInterface : CharacterItemAdapterInterface? = null
    init {
      addAllFilters(filter)
    }

    fun setData(data: List<CharacterItem>?) {
        if (data != null) {
            Log.d(TAG, "Data CharacterItem adapter ->${data.size}")
        }
        dataStorage = data
        addAllFilters(filter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(
            parent.context
        )
        val v = inflater.inflate(R.layout.item_character_item, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val character = screenData!![position]
        holder.tvKind.text = character.itemKind
        holder.tvType.text = character.type
        holder.tvValue.text = character.itemName

        holder.itemView.tag = character
    }

    override fun getItemCount(): Int {
        return if (screenData == null) {
            0
        } else {
            screenData!!.size
        }
    }

    fun setFilter(tag: String, removeFilter: Boolean) {
        if (removeFilter) {
            filter.removeAll { it == tag }
        } else {
            filter.add(tag)
        }
        Log.d(TAG, "Filter -> $filter")
        screenData = if (filter.isNullOrEmpty()) {
            ArrayList()
        } else {
            dataStorage?.stream()
                ?.filter { item: CharacterItem ->
                    filter.find { it == item.itemKind }?.count() ?: 0 > 0
                }
                ?.collect(Collectors.toList())
        }
        characterItemAdapterInterface?.onItemSetChanged( screenData.isNullOrEmpty())
        notifyDataSetChanged()
    }

    fun addAllFilters(itemTypes: List<String>?) {
        screenData = if (!itemTypes.isNullOrEmpty()) {
            filter.addAll(itemTypes)
            if (filter.isNullOrEmpty()) {
                dataStorage
            } else {
                dataStorage?.stream()
                    ?.filter { item: CharacterItem ->
                        filter.find { it == item.itemKind }?.count() ?: 0 > 0
                    }
                    ?.collect(Collectors.toList())
            }

        }else{
            dataStorage
        }
        characterItemAdapterInterface?.onItemSetChanged( screenData.isNullOrEmpty())
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvKind: TextView = itemView.findViewById(R.id.tvItemKind)
        var tvType: TextView = itemView.findViewById(R.id.tvType)
        var tvValue: TextView = itemView.findViewById(R.id.tvValue)
    }


}