/*
 * Created by Lee Oh Hyoung on 2019-08-25..
 */
package com.dnd.killcaffeine.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.model.BaseRetrofit
import com.dnd.killcaffeine.model.data.menu.Menu
import com.orhanobut.logger.Logger

class FranchiseMenuAdapter : RecyclerView.Adapter<FranchiseMenuAdapter.FranchiseMenuViewHolder>() {

    private val mFranchiseMenuArrayList: ArrayList<Menu> = ArrayList()
    private val baseUrl: String = BaseRetrofit.BASE_URL

    private var mOnFranchiseClickListener: OnFranchiseMenuClickListener? = null

    interface OnFranchiseMenuClickListener {
        fun onclick(menu: Menu)
    }

    override fun getItemCount(): Int = mFranchiseMenuArrayList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FranchiseMenuViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_franchise_menu,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: FranchiseMenuViewHolder, position: Int) {
        holder.bindTo(mFranchiseMenuArrayList[position])
    }

    fun setFranchiseMenuArrayList(list: ArrayList<Menu>) {
        mFranchiseMenuArrayList.clear()
        mFranchiseMenuArrayList.addAll(list)
        notifyDataSetChanged()
    }

    fun addFranchiseMenuArrayList(menu: Menu) {
        mFranchiseMenuArrayList.add(menu)
        notifyDataSetChanged()
    }

    fun setItemOnClickListener(onFranchiseClickListener: OnFranchiseMenuClickListener) {
        mOnFranchiseClickListener = onFranchiseClickListener
    }

    inner class FranchiseMenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val coffeeImageView: ImageView = itemView.findViewById(R.id.list_item_franchise_menu_image_view)
        private val franchiseNameTextView: TextView = itemView.findViewById(R.id.list_item_franchise_name_text_view)
        private val coffeeNameTextView: TextView = itemView.findViewById(R.id.list_item_franchise_menu_text_view)
        private val caffeineIntake: TextView = itemView.findViewById(R.id.list_item_franchise_menu_caffeine_content)

        fun bindTo(menu: Menu){
            //Logger.d("$baseUrl${menu.menuImgUrl}")

            coffeeImageView.load(R.drawable.coffee_sample) {
                crossfade(true)
                placeholder(R.drawable.background_radius_10dp_white_box)
                error(R.drawable.background_radius_10dp_white_box)
            }

            franchiseNameTextView.text = menu.franchiseName
            coffeeNameTextView.text = menu.menuName
            caffeineIntake.text = itemView.resources.getString(R.string.history_caffeine_intake, menu.caffeine.toString())

            itemView.setOnClickListener {
                mOnFranchiseClickListener?.onclick(menu)
            }
        }
    }
}