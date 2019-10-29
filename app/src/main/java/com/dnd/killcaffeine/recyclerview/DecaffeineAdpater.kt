/*
 * Created by iohyeong on 2019-08-11..
 */
package com.dnd.killcaffeine.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.di.baseUrl
import com.dnd.killcaffeine.model.data.room.menu.Menu

class DecaffeineAdpater : ListAdapter<Menu, DecaffeineAdpater.DecaffeineRecyclerViewHolder>(Menu.DIFF_CALLBACK) {

    private val mDecaffeineArrayList:ArrayList<Menu> = ArrayList()

    private var mOnItemClickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DecaffeineRecyclerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_coffee, parent, false))

    override fun getItemCount(): Int  = mDecaffeineArrayList.size

    override fun onBindViewHolder(holder: DecaffeineRecyclerViewHolder, position: Int) {
        holder.bindTo(mDecaffeineArrayList[position])
    }

    fun setDecaffeineArrayList(list : List<Menu>){
        /*mDecaffeineArrayList.clear()
        mDecaffeineArrayList.addAll(list)
        notifyDataSetChanged()*/

        submitList(list)
    }

    fun addDecaffeineArrayList(menu : Menu){
        /*mDecaffeineArrayList.add(menu)
        notifyDataSetChanged()*/

        submitList(currentList.apply {
            add(menu)
        })
    }

    fun setItemOnClickListener(itemOnClickListener: View.OnClickListener){
        mOnItemClickListener = itemOnClickListener
    }

    inner class DecaffeineRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val coffeeImageView: ImageView = itemView.findViewById(R.id.list_item_coffee_image_view)
        private val franchiseNameTextView: TextView = itemView.findViewById(R.id.list_item_coffee_franchise_name_text_view)
        private val coffeeNameTextView: TextView = itemView.findViewById(R.id.list_item_coffee_name_text_view)
        private val caffeineIntake: TextView = itemView.findViewById(R.id.list_item_coffee_caffeine_content)

        fun bindTo(menu: Menu){
            coffeeImageView.load("$baseUrl${menu.menuImgUrl}") {
                crossfade(true)
                placeholder(R.drawable.background_radius_10dp_white_box)
                error(R.drawable.background_radius_10dp_white_box)
            }

            franchiseNameTextView.text = menu.franchiseName
            coffeeNameTextView.text = menu.menuName
            caffeineIntake.text = itemView.resources.getString(R.string.history_caffeine_intake, menu.caffeine.toString())
        }
    }
}