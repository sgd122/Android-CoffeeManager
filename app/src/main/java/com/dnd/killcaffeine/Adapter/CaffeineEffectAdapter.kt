package com.dnd.killcaffeine.Adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dnd.killcaffeine.main.home.MainHomeFragment
import android.view.LayoutInflater
import android.widget.TextView
import com.dnd.killcaffeine.R

class ListAdapter(
    private val list: List<MainHomeFragment.Tip>
)
    : RecyclerView.Adapter<EffectViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EffectViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return EffectViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: EffectViewHolder, position: Int) {
        val effect: MainHomeFragment.Tip = list[position]
        holder.bind(effect)
    }

    override fun getItemCount(): Int = list.size

}

class EffectViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)) {
    private var mTitleView: TextView? = null
    private var mNumView: TextView? = null


    init {
        mTitleView = itemView.findViewById(R.id.list_title)
        mNumView = itemView.findViewById(R.id.list_num)

    }

    fun bind(tip: MainHomeFragment.Tip) {
        mTitleView?.text = tip.Title
        mNumView?.text = tip.Num.toString()
    }

}

