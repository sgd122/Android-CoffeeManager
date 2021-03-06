/*
 * Created by Lee Oh Hyoung on 2019-08-24..
 */
package com.dnd.killcaffeine.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.model.data.room.menu.Menu

class HistoryTodayAdapter : RecyclerView.Adapter<HistoryTodayAdapter.HistoryTodayViewHolder>() {

    private val mHistoryArrayList: ArrayList<Menu> = ArrayList()
    private var mOnHistoryClickListener: OnHistoryClickListener? = null

    interface OnHistoryClickListener {
        fun onClick(menu: Menu)
    }

    override fun getItemCount(): Int = mHistoryArrayList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryTodayViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item_today_history, parent, false)
        return HistoryTodayViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryTodayViewHolder, position: Int) {
        holder.bindTo(mHistoryArrayList[position])
    }

    fun setHistoryList(list: ArrayList<Menu>){
        mHistoryArrayList.clear()
        mHistoryArrayList.addAll(list)
        notifyDataSetChanged()
    }

    fun addHistory(menu: Menu){
        mHistoryArrayList.add(menu)
        notifyDataSetChanged()
    }

    fun deleteHistory(menu: Menu){
        mHistoryArrayList.remove(menu)
        notifyDataSetChanged()
    }

    fun deleteAllHistory(){
        mHistoryArrayList.clear()
        notifyDataSetChanged()
    }

    fun setOnHistoryClickListener(onHistoryClickListener: OnHistoryClickListener){
        mOnHistoryClickListener = onHistoryClickListener
    }

    inner class HistoryTodayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val historyMenu: TextView = itemView.findViewById(R.id.list_item_history_menu)
        private val historyFranchise: TextView = itemView.findViewById(R.id.list_item_history_franchise)
        private val historyCaffeineIntake: TextView = itemView.findViewById(R.id.list_item_history_caffeine_intake)

        fun bindTo(menu: Menu){
            historyMenu.text = menu.menuName
            historyFranchise.text = menu.franchiseName
            historyCaffeineIntake.text = itemView.resources.getString(R.string.history_caffeine_intake, menu.caffeine.toString())

            itemView.setOnClickListener {
                mOnHistoryClickListener?.onClick(menu)
            }
        }
    }
}