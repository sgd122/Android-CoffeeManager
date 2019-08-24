/*
 * Created by Lee Oh Hyoung on 2019-07-30 .. 
 */
package com.dnd.killcaffeine.main.home

import android.app.Activity.RESULT_OK
import android.content.Context
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
import com.dnd.killcaffeine.history.HistoryTodayActivity
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

    private val TAG = "MainHomeFragment"

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

            val totalIntake: Int = arguments?.getInt(RequestCode.TOTAL_TODAY_CAFFEINE_INTAKE_MAIN_TO_FRAGMENT, 0) ?: 0
            fragmentHomeDailyCaffeineIntakeValue.text = resources.getString(R.string.main_home_fragment_total_intake, totalIntake.toString())
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

        getFragmentBinding().fragmentHomeFrameLayout.setOnClickListener {
            startActivityForResult(Intent(activity?.applicationContext, HistoryTodayActivity::class.java), RequestCode.HISTORY_TODAY_REQUEST_CODE)
        }

        getFragmentBinding().fragmentHomeTodayDecaffeineShowMoreButton.setOnClickListener {

            // TODO: 테스트 용도이므로 나중에 지워야됨
            mDecaffeineArrayList.addAll(insertMockData())

            startActivity(Intent(activity?.applicationContext, TodayRecommendDrinkActivity::class.java).apply {
                putExtra(RequestCode.DECAFFEINE_TODAY_RECOMMEND_SHOW_MORE, mDecaffeineArrayList)
            })
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == RESULT_OK){
            when(requestCode){
                RequestCode.HISTORY_TODAY_REQUEST_CODE -> {
                    // TODO 히스토리 추가되어서 돌아오는 경우에 최근 마신음료 리스트를 갱신해야됨.
                    Log.d(TAG, "히스토리에서 돌아오기 성공!!")
                }
            }
        }
    }

    private fun insertMockData(): ArrayList<Menu> {
        return arrayListOf(
            Menu(1, "아메리카노", "R.drawable.coffee_sample", "스타벅스", 100, false),
            Menu(1, "아메리카노", "R.drawable.coffee_sample", "스타벅스", 100, false),
            Menu(1, "아메리카노", "R.drawable.coffee_sample", "스타벅스", 100, false),
            Menu(1, "아메리카노", "R.drawable.coffee_sample", "스타벅스", 100, false),
            Menu(1, "아메리카노", "R.drawable.coffee_sample", "스타벅스", 100, false),
            Menu(1, "아메리카노", "R.drawable.coffee_sample", "스타벅스", 100, false),
            Menu(1, "아메리카노", "R.drawable.coffee_sample", "스타벅스", 100, false),
            Menu(1, "아메리카노", "R.drawable.coffee_sample", "스타벅스", 100, false),
            Menu(1, "아메리카노", "R.drawable.coffee_sample", "스타벅스", 100, false),
            Menu(1, "아메리카노", "R.drawable.coffee_sample", "스타벅스", 100, false)
        )
    }
}