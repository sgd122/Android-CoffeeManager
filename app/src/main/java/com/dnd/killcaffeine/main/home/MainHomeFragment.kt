/*
 * Created by Lee Oh Hyoung on 2019-08-09 ..
 */
package com.dnd.killcaffeine.main.home

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.dnd.killcaffeine.R
import kotlinx.android.synthetic.main.home_fragment.*


class MainHomeFragment : Fragment(){

    val duration = 10
    val pixelsToMove = 30
    private val mHandler = Handler(Looper.getMainLooper())
    private val SCROLLING_RUNNABLE = object : Runnable {

        @RequiresApi(Build.VERSION_CODES.P)
        override fun run() {
            list_recycler_view.smoothScrollBy(pixelsToMove, 0)
            mHandler.postDelayed(this, duration.toLong(), 2000)

        }
    }
    /**  boilerplate(재사용) **/
    data class Tip(val Num: Int, val Title: String)
    private val caffeineEffect = listOf(
        Tip(1,". 카페인 없는 음료를 마셔보는 건 어떨까요?"),
        Tip(2,". 차츰 줄이도록 하세요 ^^"),
        Tip(3,". 물을 많이 마셔보는 건 어떨까요?")

    )
    data class Recommend(val coffeeImage: String, val coffeeLogo: String, val brand: String, val Name: String)
    private val Coffee = listOf(
        Recommend("startbucksCoffee.jpeg","startbucksLOGO.png","STARBUCKS","Americano"),
        Recommend("startbucksCoffee.jpeg","startbucksLOGO.png","STARBUCKS","Americano"),
        Recommend("startbucksCoffee.jpeg","startbucksLOGO.png","STARBUCKS","Americano")
    )




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

    return inflater.inflate(R.layout.home_fragment, container, false)

}
// populate the view now that the layout has been inflated
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    //RecyclerView node initialized here
    list_recycler_view.apply {
        // set a LinearLayoutManager to handle Android
        //RecyclerView behavior
        layoutManager = LinearLayoutManager(activity)
        // set the custom adapter to the RecyclerView
        adapter = com.dnd.killcaffeine.Adapter.ListAdapter(caffeineEffect)

    }

    recycler_today.apply{
        adapter = com.dnd.killcaffeine.Adapter.CoffeeMenuAdapter(Coffee)
        layoutManager = LinearLayoutManager(activity,HORIZONTAL,false)
    }
    /** 임시로 .. 같은 데이터 */
    recycler_fav.apply {
        adapter = com.dnd.killcaffeine.Adapter.CoffeeMenuAdapter(Coffee)
        layoutManager = LinearLayoutManager(activity,HORIZONTAL,false)
    }
  }
    companion object {
        fun newInstance(): MainHomeFragment = MainHomeFragment()
    }

}
