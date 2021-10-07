package com.mohammedragab.simpledelivryapp.ui.orderdeliveryfragrment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mohammedragab.simpledelivryapp.R
import com.mohammedragab.simpledelivryapp.databinding.ItemOrderBinding
import com.mohammedragab.simpledelivryapp.modellayer.ModelOrderDelivery

class HomeAdapter(val context:Context, val clicked: ClickeDelivery):ListAdapter<ModelOrderDelivery, HomeAdapter.HomeViewHolder>(
    diffUtileCompartort
) {

    private var  lastPosition=0
    class HomeViewHolder private  constructor(val  binding:ItemOrderBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(homeServices: ModelOrderDelivery){
            binding.model= homeServices
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent:ViewGroup): HomeViewHolder {
                val layoutInflater= LayoutInflater.from(parent.context)
                val binding=ItemOrderBinding.inflate(layoutInflater,parent,false)
                return HomeViewHolder(
                    binding

                )
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item =getItem(position)
        holder.bind(item)
        holder.binding.click=clicked
        Glide.with(holder.binding.imageView2)
            .load(R.drawable.pharmcy_image)
            .into(holder.binding.imageView2)

    }

companion object{
    val diffUtileCompartort= object:DiffUtil.ItemCallback<ModelOrderDelivery>(){
        override fun areItemsTheSame(
            oldItem: ModelOrderDelivery,
            newItem: ModelOrderDelivery
        ): Boolean {
            return  oldItem.orderId==newItem.orderId

        }

        override fun areContentsTheSame(
            oldItem: ModelOrderDelivery,
            newItem: ModelOrderDelivery
        ): Boolean {
            return  oldItem==newItem
        }


    }

}

}
