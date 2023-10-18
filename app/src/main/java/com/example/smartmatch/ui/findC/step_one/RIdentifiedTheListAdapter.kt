package com.example.eazylight.ui.find_C.step_one.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smartmatch.R
import com.example.smartmatch.chart.FindCTBtnParams
import com.example.smartmatch.base.RecyclerViewAdapterHelper
import com.example.smartmatch.databinding.ItemFindCtBinding

class RvIdentifiedTheCListAdapter(private val findCTBtnParamsList: List<FindCTBtnParams<View>>) :
    RecyclerView.Adapter<RecyclerViewAdapterHelper<ItemFindCtBinding>.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapterHelper<ItemFindCtBinding>.ViewHolder {
        return RecyclerViewAdapterHelper<ItemFindCtBinding>()
            .createBindingViewHolder(R.layout.item_find_ct, parent, false)
    }

    override fun onBindViewHolder(
        holder: RecyclerViewAdapterHelper<ItemFindCtBinding>.ViewHolder,
        position: Int
    ) {
        holder.binding.setCtViewParams(findCTBtnParamsList[position])
    }

    override fun getItemCount(): Int {
        return findCTBtnParamsList.size
    }
}
