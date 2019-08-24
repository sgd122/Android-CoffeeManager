/*
 * Created by Lee Oh Hyoung on 2019-08-24..
 */
package com.dnd.killcaffeine.history.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.model.data.history.History

class HistoryTodayAdapter : RecyclerView.Adapter<HistoryTodayAdapter.HistoryTodayViewHolder>() {

    private val mHistoryArrayList: ArrayList<History> = ArrayList()

    override fun getItemCount(): Int = mHistoryArrayList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryTodayViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item_today_history, parent, false)
        return HistoryTodayViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryTodayViewHolder, position: Int) {
        mHistoryArrayList[position].run {
            holder.historyMenu.text = menuName
            holder.historyFranchise.text = franchiseName
            holder.historyCaffeineIntake.text = holder.itemView.resources.getString(R.string.history_caffeine_intake, "$caffeine")
        }
    }

    fun setHistoryList(list: ArrayList<History>){
        mHistoryArrayList.clear()
        mHistoryArrayList.addAll(list)
        notifyDataSetChanged()
    }

    fun addHistory(history: History){
        mHistoryArrayList.add(history)
        notifyDataSetChanged()
    }

    fun deleteHistory(history: History){
        mHistoryArrayList.remove(history)
        notifyDataSetChanged()
    }

    fun deleteAllHistory(){
        mHistoryArrayList.clear()
        notifyDataSetChanged()
    }

    inner class HistoryTodayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val historyMenu: TextView = itemView.findViewById(R.id.list_item_history_menu)
        val historyFranchise: TextView = itemView.findViewById(R.id.list_item_history_franchise)
        val historyCaffeineIntake: TextView = itemView.findViewById(R.id.list_item_history_caffeine_intake)
    }
}