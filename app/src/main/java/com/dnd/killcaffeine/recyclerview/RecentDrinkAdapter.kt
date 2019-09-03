package com.dnd.killcaffeine.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.model.BaseRetrofit
import com.dnd.killcaffeine.model.data.room.menu.Menu

/*
 * Created by iohyeong on 2019-08-11..
 */
class RecentDrinkAdapter : RecyclerView.Adapter<RecentDrinkAdapter.RecentDrinkRecyclerViewHolder>() {

    private val mDRecentDrinkArrayList:ArrayList<Menu> = ArrayList()
    private val baseUrl: String = BaseRetrofit.BASE_URL

    private var mOnItemClickListener: View.OnClickListener? = null
    private var mOnRecentDrinkItemClickListener: OnRecentDrinkItemClickListener? = null
    
    interface OnRecentDrinkItemClickListener {
        fun onItemClick(menu: Menu)
    }

    override fun getItemCount(): Int  = mDRecentDrinkArrayList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RecentDrinkRecyclerViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item_coffee, parent, false))

    override fun onBindViewHolder(holder: RecentDrinkRecyclerViewHolder, position: Int) {
        holder.bindTo(mDRecentDrinkArrayList[position])
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
        private val coffeeImageView: ImageView = itemView.findViewById(R.id.list_item_coffee_image_view)
        private val franchiseNameTextView: TextView = itemView.findViewById(R.id.list_item_coffee_franchise_name_text_view)
        private val coffeeNameTextView: TextView = itemView.findViewById(R.id.list_item_coffee_name_text_view)
        private val caffeineIntake: TextView = itemView.findViewById(R.id.list_item_coffee_caffeine_content)

        //"$baseUrl${menu.menuImgUrl}"

        fun bindTo(menu: Menu){
            coffeeImageView.load(R.drawable.coffee_sample) {
                crossfade(true)
                placeholder(R.drawable.background_radius_10dp_white_box)
                error(R.drawable.background_radius_10dp_white_box)
            }

            franchiseNameTextView.text = menu.franchiseName
            coffeeNameTextView.text = menu.menuName
            caffeineIntake.text = itemView.resources.getString(R.string.history_caffeine_intake, menu.caffeine.toString())

            itemView.setOnClickListener {
                mOnRecentDrinkItemClickListener?.onItemClick(menu)
            }
        }
    }
}