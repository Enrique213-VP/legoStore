package com.svape.legostore.ui.fragment.dashboard.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.svape.legostore.core.BaseViewHolder
import com.svape.legostore.data.model.store.Store
import com.svape.legostore.databinding.StoreItemBinding

class StoreAdapter(
    private val storeList: List<Store>,
    private val itemClickListener: OnStoreClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnStoreClickListener {
        fun onStoreClick(store: Store)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            StoreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = StoreViewHolder(itemBinding, parent.context)

        itemBinding.root.setOnClickListener {
            val position =
                holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                    ?: return@setOnClickListener
            itemClickListener.onStoreClick(storeList[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is StoreViewHolder -> holder.bind(storeList[position])
        }
    }

    override fun getItemCount(): Int = storeList.size

    private inner class StoreViewHolder(
        val binding: StoreItemBinding,
        val context: Context
    ) : BaseViewHolder<Store>(binding.root) {
        override fun bind(item: Store) {
            Glide.with(context)
                .load("${item.image}")
                .centerCrop()
                .transition(com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade())
                .error(android.R.drawable.ic_dialog_alert)
                .into(binding.imageProduct)
        }
    }
}