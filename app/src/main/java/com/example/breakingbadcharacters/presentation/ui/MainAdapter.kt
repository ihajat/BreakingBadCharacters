package com.example.breakingbadcharacters.presentation.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.breakingbadcharacters.R
import com.example.breakingbadcharacters.domain.model.BBCharacter
import kotlinx.android.synthetic.main.activity_detailed.view.*

class MainAdapter : RecyclerView.Adapter<ViewHolder>(), Filterable {
    private var items = listOf<BBCharacter>()
    private var itemsAll = listOf<BBCharacter>()
    private var onNothingFound: (() -> Unit)? = null
    private var lowerCaseSearcString = ""
    private var seasons = mutableMapOf<Int, Boolean>()

    inner class MainViewHolder(itemView: View) : ViewHolder(itemView) {
        val bbccharacterName = itemView.bbccharacter_name
        val bbccharacterImage = itemView.bbccharacter_image
        val requestOptions = RequestOptions()
            .circleCrop()
            .error(R.drawable.ic_launcher_background)
            .placeholder(R.drawable.ic_launcher_background)

        fun bind(bbCharacter: BBCharacter) {
            bbccharacterName.text = bbCharacter.name.trim()
            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(bbCharacter.img)
                .into(bbccharacterImage)


            itemView.setOnClickListener {
                val intent = Intent(it.context, DetailedActivity::class.java)
                val bbcharacter = items.get(position)
                intent.putExtra("bbcharacter", bbcharacter)
                it.context.startActivity(intent)
            }
        }
    }

    fun setData(items: List<BBCharacter>) {
        this.items = items
        this.itemsAll = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_bbcharacter_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is MainViewHolder -> {
                holder.bind(items.get(position))
            }
        }
    }

    override fun getItemCount(): Int = items.size

    fun isSeason(item: BBCharacter): Boolean {
        for ((k, v) in seasons) {
            if (v) {
                if (!item.appearance.contains(k)) {
                    return false
                }
            }
        }
        return true
    }

    fun setSeason(index: Int, status: Boolean) {
        seasons[index] = status
        var searchableList: MutableList<BBCharacter> = arrayListOf()

        for (item in itemsAll) {
            if (item.name.toLowerCase().contains(lowerCaseSearcString) && isSeason(item)) {
                searchableList.add(item)
            }
        }
        items = searchableList

        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        var searchableList: MutableList<BBCharacter> = arrayListOf()

        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                lowerCaseSearcString = charSequence.toString().toLowerCase()
                searchableList.clear()
                if (charSequence.isNullOrBlank()) {
                    for (item in itemsAll) {
                        if (isSeason(item)
                        ) {
                            searchableList.add(item)
                        }
                    }
                } else {
                    for (item in itemsAll) {
                        if (item.name.toLowerCase()
                                .contains(lowerCaseSearcString) && isSeason(item)
                        ) {
                            searchableList.add(item)
                        }
                    }
                }

                var filterResults = FilterResults()
                filterResults.values = searchableList
                return filterResults

            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                if (searchableList.isNullOrEmpty()) onNothingFound?.invoke()
                items = searchableList

                notifyDataSetChanged()
            }

        }
    }

}