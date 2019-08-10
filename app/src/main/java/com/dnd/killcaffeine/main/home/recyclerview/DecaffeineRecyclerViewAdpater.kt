/*
 * Created by iohyeong on 2019-08-11..
 */
package com.dnd.killcaffeine.main.home.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.model.data.menu.Menu

class DecaffeineRecyclerViewAdpater : RecyclerView.Adapter<DecaffeineRecyclerViewAdpater.DecaffeineRecyclerViewHolder>() {

    private val mDecaffeineArrayList = ArrayList<Menu>()

    private var mOnItemClickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DecaffeineRecyclerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_coffee, parent, false))

    override fun getItemCount(): Int  = mDecaffeineArrayList.size

    override fun onBindViewHolder(holder: DecaffeineRecyclerViewHolder, position: Int) {
        mDecaffeineArrayList[position].run {
            with(holder){
                Glide.with(holder.itemView.context).load(menuImgUrl).into(coffeeImageView)
                franchiseNameTextView.text = franchiseName
                coffeeNameTextView.text = menuName

                /*itemView.setOnClickListener {
                    Toast.makeText(itemView.context, "프랜차이즈 : $franchiseName", Toast.LENGTH_SHORT).show()
                }*/
            }
        }

    }

    fun setDecaffeineArrayList(list : ArrayList<Menu>){
        mDecaffeineArrayList.clear()
        mDecaffeineArrayList.addAll(list)
        notifyDataSetChanged()
    }

    fun addDecaffeineArrayList(menu : Menu){
        mDecaffeineArrayList.add(menu)
        notifyDataSetChanged()
    }

    fun setItemOnClickListener(itemOnClickListener: View.OnClickListener){
        mOnItemClickListener = itemOnClickListener
    }

    inner class DecaffeineRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val coffeeImageView: ImageView = itemView.findViewById(R.id.list_item_coffee_image_view)
        val franchiseNameTextView: TextView = itemView.findViewById(R.id.list_item_coffee_franchise_name_text_view)
        val coffeeNameTextView: TextView = itemView.findViewById(R.id.list_item_coffee_name_text_view)
    }
}