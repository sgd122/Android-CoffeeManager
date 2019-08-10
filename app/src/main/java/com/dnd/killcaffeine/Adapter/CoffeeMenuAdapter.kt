package com.dnd.killcaffeine.Adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dnd.killcaffeine.main.home.MainHomeFragment
import android.view.LayoutInflater
import android.view.Menu
import android.widget.ImageView
import android.widget.TextView
import com.dnd.killcaffeine.R

class CoffeeMenuAdapter(
    private val coffeelist: List<MainHomeFragment.Recommend>
)
    : RecyclerView.Adapter<MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MenuViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val recommend: MainHomeFragment.Recommend = coffeelist[position]
        holder.bind(recommend)
    }

    override fun getItemCount(): Int = coffeelist.size



}

class MenuViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.recommend_item, parent, false)) {
    private var mCoffeeName: TextView? = null
    private var mCoffeeBrand: TextView? = null
    private var mCoffeeImage: ImageView? = null
    private var mCoffeeLogo: ImageView? = null

    init {
        mCoffeeName = itemView.findViewById(R.id.coffeeName)
        mCoffeeBrand = itemView.findViewById(R.id.coffeeBrand)
        mCoffeeImage = itemView.findViewById(R.id.coffeeImage)
        mCoffeeLogo = itemView.findViewById(R.id.coffeeLogo)

    }

    fun bind(recommend: MainHomeFragment.Recommend) {
        mCoffeeName?.text = recommend.Name
        mCoffeeBrand?.text = recommend.brand
        mCoffeeImage?.setImageResource(R.drawable.ic_launcher_foreground)
        mCoffeeLogo?.setImageResource(R.drawable.ic_mtrl_chip_checked_black)

    }

}

