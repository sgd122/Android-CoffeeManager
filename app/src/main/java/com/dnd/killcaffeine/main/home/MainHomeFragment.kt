/*
 * Created by Lee Oh Hyoung on 2019-07-30 .. 
 */
package com.dnd.killcaffeine.main.home

import android.content.Intent
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.RequestCode
import com.dnd.killcaffeine.base.BaseFragment
import com.dnd.killcaffeine.databinding.FragmentHomeBinding
import com.dnd.killcaffeine.detail.DetailActivity
import com.dnd.killcaffeine.main.home.recyclerview.DecaffeineRecyclerViewAdpater
import com.dnd.killcaffeine.main.home.recyclerview.RecentDrinkRecyclerViewAdapter
import com.dnd.killcaffeine.main.home.show_more.TodayRecommendDrinkActivity
import com.dnd.killcaffeine.model.data.menu.Menu
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainHomeFragment : BaseFragment<FragmentHomeBinding, MainHomeViewModel>() {

    companion object {
        fun newInstance() = MainHomeFragment()
    }

    private val TAG = javaClass.name

    override val mViewModel: MainHomeViewModel by viewModel()
    override val resourceId: Int
        get() = R.layout.fragment_home

    private val mDecaffeineRecyclerViewAdapter: DecaffeineRecyclerViewAdpater by inject()
    private val mRecentRecyclerViewAdapter: RecentDrinkRecyclerViewAdapter by inject()

    private val mDecaffeineArrayList: ArrayList<Menu> = ArrayList()

    override fun initViewStart() {
        mDecaffeineRecyclerViewAdapter.apply {
            setDecaffeineArrayList(insertMockData())
        }

        mRecentRecyclerViewAdapter.apply {
            setRecentDrinkArrayList(insertMockData())
            setOnRecentDrinkItemClickListener(object: RecentDrinkRecyclerViewAdapter.OnRecentDrinkItemClickListener {
                override fun onItemClick(menu: Menu) {
                    startActivity(Intent(activity?.applicationContext, DetailActivity::class.java).apply { putExtra("menu", menu) })
                }
            })
        }

        getFragmentBinding().run {
            fragmentHomeTodayDecaffeineRecyclerView.apply {
                layoutManager = LinearLayoutManager(activity?.applicationContext, RecyclerView.HORIZONTAL, false)
                adapter = mDecaffeineRecyclerViewAdapter
            }

            fragmentHomeCoffeeRecentRecyclerView.apply {
                layoutManager = LinearLayoutManager(activity?.applicationContext, RecyclerView.HORIZONTAL, false)
                adapter = mRecentRecyclerViewAdapter
            }
        }
    }

    override fun initDataBinding() {
        mViewModel.decaffeineMenuLiveData.observe(this, Observer { result ->
            result?.let {

                with(it.list){
                    // 오늘의 대체 음료 추천
                    mDecaffeineRecyclerViewAdapter.setDecaffeineArrayList(this)
                    mDecaffeineArrayList.addAll(this)

                    // 최근에 마신 음료
                    mRecentRecyclerViewAdapter.setRecentDrinkArrayList(this)
                }
            }
        })

        mViewModel.franchiseMenuLiveData.observe(this, Observer { result ->
            result?.let {
                Log.d(TAG, "프랜차이즈 메뉴 사이즈 : ${result.list.size}")
                it.list.forEachIndexed { index, franchise ->
                    Log.d(TAG, "$index 번째 프랜차이즈 : ${franchise.franchiseName}")
                    Log.d(TAG, "[메뉴]")
                    franchise.menu.forEach { menu ->
                        Log.d(TAG, "$menu")
                    }
                }
            } ?: Log.d(TAG, "프랜차이즈 API call 실패")
        })
    }

    override fun initViewFinal() {
        mViewModel.getDecaffeineMenuList()

        // TODO : 프랜차이즈 메뉴 호출
        //mViewModel.getFranchiseMenuList()

        getFragmentBinding().fragmentHomeTodayDecaffeineShowMoreButton.setOnClickListener {

            // TODO: 테스트 용도이므로 나중에 지워야됨
            mDecaffeineArrayList.addAll(insertMockData())

            startActivity(Intent(activity?.applicationContext, TodayRecommendDrinkActivity::class.java).apply {
                putExtra(RequestCode.DECAFFEINE_TODAY_RECOMMEND_SHOW_MORE, mDecaffeineArrayList)
            })
        }
    }

    private fun insertMockData(): ArrayList<Menu> {
        return arrayListOf(
            Menu(1, "아메리카노", "R.drawable.background_list_item_radius_10dp_shape", "스타벅스", 100, false),
            Menu(1, "아메리카노", "R.drawable.background_list_item_radius_10dp_shape", "스타벅스", 100, false),
            Menu(1, "아메리카노", "R.drawable.background_list_item_radius_10dp_shape", "스타벅스", 100, false),
            Menu(1, "아메리카노", "R.drawable.background_list_item_radius_10dp_shape", "스타벅스", 100, false),
            Menu(1, "아메리카노", "R.drawable.background_list_item_radius_10dp_shape", "스타벅스", 100, false),
            Menu(1, "아메리카노", "R.drawable.background_list_item_radius_10dp_shape", "스타벅스", 100, false),
            Menu(1, "아메리카노", "R.drawable.background_list_item_radius_10dp_shape", "스타벅스", 100, false),
            Menu(1, "아메리카노", "R.drawable.background_list_item_radius_10dp_shape", "스타벅스", 100, false),
            Menu(1, "아메리카노", "R.drawable.background_list_item_radius_10dp_shape", "스타벅스", 100, false),
            Menu(1, "아메리카노", "R.drawable.background_list_item_radius_10dp_shape", "스타벅스", 100, false)
        )
    }
}