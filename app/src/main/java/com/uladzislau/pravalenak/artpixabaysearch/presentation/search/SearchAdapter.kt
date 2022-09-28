package com.uladzislau.pravalenak.artpixabaysearch.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uladzislau.pravalenak.artpixabaysearch.databinding.ItemImageSearchBinding

class SearchAdapter : RecyclerView.Adapter<SearchImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchImageViewHolder =
        SearchImageViewHolder(ItemImageSearchBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: SearchImageViewHolder, position: Int) {
        holder.onBind()
    }

    override fun getItemCount(): Int = 0
}

class SearchImageViewHolder(private val binding: ItemImageSearchBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind() {

    }
}