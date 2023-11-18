package com.example.smartmatch.ui.construction.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @className: TreeViewHolder
 * @author: Voyager
 * @description: 树状显示的ViewHolder,暂时弃用
 * @date:  2023/11/18 15:10
 * @version 1.0
 **/
sealed class TreeViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
class ScenarioViewHolder(itemView: View):TreeViewHolder(itemView){

}
class AreaViewHolder(itemView: View):TreeViewHolder(itemView){

}
class NetViewHolder(itemView: View):TreeViewHolder(itemView){

}