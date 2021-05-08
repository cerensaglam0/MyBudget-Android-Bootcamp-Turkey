package com.saglamceren.mybudget.ui.main

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.saglamceren.mybudget.R
import com.saglamceren.mybudget.data.Currency
import com.saglamceren.mybudget.data.SpendingCategory
import com.saglamceren.mybudget.data.local.model.Spending
import com.saglamceren.mybudget.databinding.ItemSpendingBinding
import com.saglamceren.mybudget.extension.toMoney

class SpendingAdapter(
    private val itemList: List<Spending>,
    private val currency: Currency,
    private val callback: SpendingAdapterCallback
) : RecyclerView.Adapter<SpendingAdapter.SpendingViewHolder>() {
    class SpendingViewHolder(val binding: ItemSpendingBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpendingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = ItemSpendingBinding.inflate(layoutInflater, parent, false)
        return SpendingViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SpendingViewHolder, position: Int) {
        val spending = itemList[position]
        holder.binding.data = spending

        when (spending.category) {
            SpendingCategory.RENT.id -> {
                holder.binding.imageViewRecyclerView.setImageResource(R.drawable.ic_house)
                ImageViewCompat.setImageTintList(holder.binding.imageViewRecyclerView, ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.pink_800)))
                holder.binding.imageViewRecyclerView.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.pink_800_20))
            }
            SpendingCategory.INVOICE.id -> {
                holder.binding.imageViewRecyclerView.setImageResource(R.drawable.ic_invoice)
                ImageViewCompat.setImageTintList(holder.binding.imageViewRecyclerView, ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.deep_purple_800)))
                holder.binding.imageViewRecyclerView.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.deep_purple_800_20))
            }
            SpendingCategory.OTHER.id -> {
                holder.binding.imageViewRecyclerView.setImageResource(R.drawable.ic_shopping_cart)
                ImageViewCompat.setImageTintList(holder.binding.imageViewRecyclerView, ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.light_blue)))
                holder.binding.imageViewRecyclerView.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.light_blue_20))
            }
        }

        holder.binding.textViewMoneyRecyclerView.text = spending.money.toMoney(currency = currency)

        holder.itemView.setOnClickListener {
            callback.onSpendingItemClick(spending, holder.binding.textViewMoneyRecyclerView.text.toString())
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}