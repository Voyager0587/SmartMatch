package com.example.smartmatch.ui.choiceT.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.smartmatch.R
import com.example.smartmatch.base.RecyclerViewAdapterHelper
import com.example.smartmatch.chart.checkTAll

/**
 * 显示绑定灯的RV适配器
 */
class ChoiceTRvAdapter():ListAdapter<checkTAll,ChoiceTRvAdapter.MyViewHolder>(findtCallBack){
    object findtCallBack:DiffUtil.ItemCallback<checkTAll>(){
        override fun areItemsTheSame(oldItem: checkTAll, newItem: checkTAll): Boolean {
           return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: checkTAll, newItem: checkTAll): Boolean {
            return oldItem.tid.equals(newItem.tid)
        }

    }
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val tid=itemView.findViewById(R.id.name) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return  MyViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data=getItem(position)
        holder.tid.text="${data.tid}号灯"


    }


}