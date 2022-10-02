package com.uladzislau.pravalenak.artpixabaysearch.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.uladzislau.pravalenak.artpixabaysearch.databinding.ItemImageSearchBinding

class SearchAdapter(
    val onCLickListener: (Int) -> Unit
) : RecyclerView.Adapter<SearchImageViewHolder>() {

    private val items: MutableList<HitUI> = mutableListOf()

    fun update(hits: List<HitUI>) {
        items.clear()
        items.addAll(hits)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchImageViewHolder =
        SearchImageViewHolder(
            ItemImageSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SearchImageViewHolder, position: Int) {
        val item = items[position]
        holder.onBind(item)
        holder.itemView.setOnClickListener { onCLickListener(item.id) }
    }

    override fun getItemCount(): Int = items.size
}

class SearchImageViewHolder(private val binding: ItemImageSearchBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(hitUI: HitUI) {
        binding.authorNameTV.text = hitUI.userName
        binding.contentIV.load(hitUI.url)
        binding.tagsTV.text = hitUI.tags.joinToString()
    }
}