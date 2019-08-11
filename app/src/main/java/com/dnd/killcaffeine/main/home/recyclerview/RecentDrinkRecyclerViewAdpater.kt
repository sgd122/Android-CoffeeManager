package com.dnd.killcaffeine.main.home.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.model.BaseRetrofit
import com.dnd.killcaffeine.model.data.menu.Menu

/*
 * Created by iohyeong on 2019-08-11..
 */
class RecentDrinkRecyclerViewAdpater : RecyclerView.Adapter<RecentDrinkRecyclerViewAdpater.RecentDrinkRecyclerViewHolder>() {

    private val mDRecentDrinkArrayList = ArrayList<Menu>()

    private var mOnItemClickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RecentDrinkRecyclerViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item_coffee, parent, false))

    override fun getItemCount(): Int  = mDRecentDrinkArrayList.size

    override fun onBindViewHolder(holder: RecentDrinkRecyclerViewHolder, position: Int) {
        mDRecentDrinkArrayList[position].run {
            with(holder){
                Glide.with(holder.itemView.context)
                    .load("${BaseRetrofit.BASE_URL}$menuImgUrl")
                    .into(coffeeImageView)

                coffeeImageView.background = ContextCompat.getDrawable(itemView.context, R.drawable.background_list_item_radius_10dp_shape)
                coffeeImageView.clipToOutline = true

                franchiseNameTextView.text = franchiseName
                coffeeNameTextView.text = menuName

                /*itemView.setOnClickListener {
                    Toast.makeText(itemView.context, "프랜차이즈 : $franchiseName", Toast.LENGTH_SHORT).show()
                }*/
            }
        }

    }

    fun setRecentDrinkArrayList(list : ArrayList<Menu>){
        mDRecentDrinkArrayList.clear()
        mDRecentDrinkArrayList.addAll(list)
        notifyDataSetChanged()
    }

    fun addRecentDrinkArrayList(menu : Menu){
        mDRecentDrinkArrayList.add(menu)
        notifyDataSetChanged()
    }

    fun setItemOnClickListener(itemOnClickListener: View.OnClickListener){
        mOnItemClickListener = itemOnClickListener
    }

    inner class RecentDrinkRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val coffeeImageView: ImageView = itemView.findViewById(R.id.list_item_coffee_image_view)
        val franchiseNameTextView: TextView = itemView.findViewById(R.id.list_item_coffee_franchise_name_text_view)
        val coffeeNameTextView: TextView = itemView.findViewById(R.id.list_item_coffee_name_text_view)
    }
}