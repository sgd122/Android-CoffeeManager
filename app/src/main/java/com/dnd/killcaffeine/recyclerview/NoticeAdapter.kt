/*
 * Created by Lee Oh Hyoung on 2019-08-27..
 */
package com.dnd.killcaffeine.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.model.data.response.Notice

class NoticeAdapter : RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>() {

    interface OnNoticeClickListener {
        fun onClick(notice: Notice)
    }

    fun NoticeAdapter(noticeClickListener: OnNoticeClickListener){
        mNoticeClickListener = noticeClickListener
    }

    private val mNoticeList = ArrayList<Notice>()

    private lateinit var mNoticeClickListener: OnNoticeClickListener

    override fun getItemCount(): Int = mNoticeList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item_notice, parent, false)
        return NoticeViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) = holder.bindTo(mNoticeList[position])

    fun setNoticeList(list: ArrayList<Notice>){
        mNoticeList.clear()
        mNoticeList.addAll(list)
        notifyDataSetChanged()
    }

    fun addNotice(notice: Notice){
        mNoticeList.add(notice)
        notifyDataSetChanged()
    }

    fun setOnNoticeClickListener(noticeClickListener: OnNoticeClickListener){
        this.mNoticeClickListener = noticeClickListener
    }

    inner class NoticeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val writeTime: TextView = itemView.findViewById(R.id.list_item_notice_write_time)
        private val title: TextView = itemView.findViewById(R.id.list_item_notice_title)

        fun bindTo(notice: Notice){
            writeTime.text = notice.writeTime
            title.text = notice.title

            itemView.setOnClickListener {
                if(::mNoticeClickListener.isInitialized){
                    mNoticeClickListener.onClick(notice)
                }
            }
        }

    }
}