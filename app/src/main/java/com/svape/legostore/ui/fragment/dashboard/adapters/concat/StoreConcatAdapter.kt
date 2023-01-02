package com.svape.legostore.ui.fragment.dashboard.adapters.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.svape.legostore.core.BaseConcatHolder
import com.svape.legostore.databinding.StoreAllRowBinding
import com.svape.legostore.ui.fragment.dashboard.adapters.StoreAdapter

class StoreConcatAdapter(private val storeAdapter: StoreAdapter) :
    RecyclerView.Adapter<BaseConcatHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding =
            StoreAllRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when (holder) {
            is ConcatViewHolder -> holder.bind(storeAdapter)
        }
    }

    override fun getItemCount(): Int = 1

    private inner class ConcatViewHolder(val binding: StoreAllRowBinding) :
        BaseConcatHolder<StoreAdapter>(binding.root) {
        override fun bind(adapter: StoreAdapter) {
            binding.rvProducts.adapter = adapter
        }

    }


}