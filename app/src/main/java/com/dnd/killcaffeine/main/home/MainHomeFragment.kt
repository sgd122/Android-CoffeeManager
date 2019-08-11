/*
 * Created by Lee Oh Hyoung on 2019-07-30 .. 
 */
package com.dnd.killcaffeine.main.home

import android.content.Context
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.base.BaseFragment
import com.dnd.killcaffeine.databinding.FragmentHomeBinding
import com.dnd.killcaffeine.main.home.recyclerview.DecaffeineRecyclerViewAdpater
import com.dnd.killcaffeine.main.home.recyclerview.RecentDrinkRecyclerViewAdpater
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
    private val mRecentRecyclerViewAdapter: RecentDrinkRecyclerViewAdpater by inject()

    override fun initViewStart() {
//        mDecaffeineRecyclerViewAdapter.apply {
//            setDecaffeineArrayList(insertMockData(activity?.applicationContext))
//        }

//        mRecentRecyclerViewAdapter.apply {
//            setRecentDrinkArrayList(insertMockData(activity?.applicationContext))
//        }

        getFragmentBinding().run {
            fragmentHomeTodayDecaffeineRecyclerView.apply {
                layoutManager = LinearLayoutManager(activity?.applicationContext, RecyclerView.HORIZONTAL, false)
                layoutManager?.isAutoMeasureEnabled = true
                isNestedScrollingEnabled = false
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
                Log.d(TAG, "디카페인 메뉴 사이즈 : ${result.list.size}")
                mDecaffeineRecyclerViewAdapter.setDecaffeineArrayList(it.list)
                mRecentRecyclerViewAdapter.setRecentDrinkArrayList(it.list)

            } ?: Log.d(TAG, "디카페인 API call 실패")
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

        //mViewModel.getFranchiseMenuList()
    }

    private fun insertMockData(context: Context?): ArrayList<Menu> {
        context?.let {
            return arrayListOf(
                Menu(1, "아메리카노", "R.drawable.app_icon", "스타벅스", 100, false),
                Menu(1, "아메리카노", "R.drawable.app_icon", "스타벅스", 100, false),
                Menu(1, "아메리카노", "R.drawable.app_icon", "스타벅스", 100, false),
                Menu(1, "아메리카노", "R.drawable.app_icon", "스타벅스", 100, false),
                Menu(1, "아메리카노", "R.drawable.app_icon", "스타벅스", 100, false),
                Menu(1, "아메리카노", "R.drawable.app_icon", "스타벅스", 100, false),
                Menu(1, "아메리카노", "R.drawable.app_icon", "스타벅스", 100, false),
                Menu(1, "아메리카노", "R.drawable.app_icon", "스타벅스", 100, false),
                Menu(1, "아메리카노", "R.drawable.app_icon", "스타벅스", 100, false)
            )
        } ?: return arrayListOf()
    }
}