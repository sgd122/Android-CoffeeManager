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
import com.dnd.killcaffeine.model.data.menu.Menu

class FranchiseMenuAdapter : RecyclerView.Adapter<FranchiseMenuAdapter.FranchiseMenuViewHolder>() {

    private val mFranchiseMenuArrayList: ArrayList<Menu> = ArrayList()

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
        mFranchiseMenuArrayList[position].run {
            with(holder) {
                coffeeImageView.load(R.drawable.coffee_sample) {
                    crossfade(true)
                    placeholder(R.drawable.background_radius_10dp_white_box)
                    error(R.drawable.background_radius_10dp_white_box)
                }

                franchiseNameTextView.text = franchiseName
                coffeeNameTextView.text = menuName
                caffeineIntake.text = itemView.resources.getString(R.string.history_caffeine_intake, caffeine.toString())

                itemView.setOnClickListener {
                    mOnFranchiseClickListener?.onclick(this@run)
                }
            }
        }

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
        val coffeeImageView: ImageView = itemView.findViewById(R.id.list_item_franchise_menu_image_view)
        val franchiseNameTextView: TextView = itemView.findViewById(R.id.list_item_franchise_name_text_view)
        val coffeeNameTextView: TextView = itemView.findViewById(R.id.list_item_franchise_menu_text_view)
        val caffeineIntake: TextView = itemView.findViewById(R.id.list_item_franchise_menu_caffeine_content)
    }
}