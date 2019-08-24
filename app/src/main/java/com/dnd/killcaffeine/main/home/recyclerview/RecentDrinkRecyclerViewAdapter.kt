package com.dnd.killcaffeine.main.home.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.model.BaseRetrofit
import com.dnd.killcaffeine.model.data.menu.Menu

/*
 * Created by iohyeong on 2019-08-11..
 */
class RecentDrinkRecyclerViewAdapter : RecyclerView.Adapter<RecentDrinkRecyclerViewAdapter.RecentDrinkRecyclerViewHolder>() {

    private val mDRecentDrinkArrayList:ArrayList<Menu> = ArrayList()

    private var mOnItemClickListener: View.OnClickListener? = null
    private var mOnRecentDrinkItemClickListener: OnRecentDrinkItemClickListener? = null
    
    interface OnRecentDrinkItemClickListener {
        fun onItemClick(menu: Menu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RecentDrinkRecyclerViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item_coffee, parent, false))

    override fun getItemCount(): Int  = mDRecentDrinkArrayList.size

    override fun onBindViewHolder(holder: RecentDrinkRecyclerViewHolder, position: Int) {
        val imageUrl = BaseRetrofit.BASE_URL
        when(imageUrl.startsWith("http://")){
            true -> imageUrl.replace("http://", "https://")
        }

        mDRecentDrinkArrayList[position].run {
            with(holder){
                //load("$imageUrl$menuImgUrl")
                coffeeImageView.load(R.drawable.coffee_sample) {
                    crossfade(true)
                    placeholder(R.drawable.background_radius_10dp_white_box)
                    error(R.drawable.background_radius_10dp_white_box)
                    transformations(RoundedCornersTransformation(10.0f))
                }

                franchiseNameTextView.text = franchiseName
                coffeeNameTextView.text = menuName

                itemView.setOnClickListener {
                    mOnRecentDrinkItemClickListener?.onItemClick(this@run)
                }
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

    fun setOnRecentDrinkItemClickListener(recentDrinkItemClickListener: OnRecentDrinkItemClickListener){
        mOnRecentDrinkItemClickListener = recentDrinkItemClickListener
    }

    inner class RecentDrinkRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val coffeeImageView: ImageView = itemView.findViewById(R.id.list_item_coffee_image_view)
        val franchiseNameTextView: TextView = itemView.findViewById(R.id.list_item_coffee_franchise_name_text_view)
        val coffeeNameTextView: TextView = itemView.findViewById(R.id.list_item_coffee_name_text_view)
    }
}