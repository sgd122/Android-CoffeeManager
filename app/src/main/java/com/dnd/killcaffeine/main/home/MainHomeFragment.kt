/*
 * Created by Lee Oh Hyoung on 2019-07-30 .. 
 */
package com.dnd.killcaffeine.main.home

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.RequestCode
import com.dnd.killcaffeine.base.BaseFragment
import com.dnd.killcaffeine.databinding.FragmentHomeBinding
import com.dnd.killcaffeine.detail.DetailActivity
import com.dnd.killcaffeine.history.HistoryTodayActivity
import com.dnd.killcaffeine.recyclerview.DecaffeineAdpater
import com.dnd.killcaffeine.recyclerview.RecentDrinkAdapter
import com.dnd.killcaffeine.main.home.show_more.TodayRecommendDrinkActivity
import com.dnd.killcaffeine.model.data.menu.Menu
import com.orhanobut.logger.Logger
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

    private val mDecaffeineRecyclerViewAdapter: DecaffeineAdpater by inject()
    private val mRecentRecyclerViewAdapter: RecentDrinkAdapter by inject()

    private val mDecaffeineArrayList: ArrayList<Menu> = ArrayList()

    private var totalIntakeFromSplash: Int = 0 // Splash 에서 넘겨받은 일일카페인 섭취량

    override fun initViewStart() {
        mDecaffeineRecyclerViewAdapter.apply {
            setDecaffeineArrayList(insertMockData())
        }

        mRecentRecyclerViewAdapter.apply {
            setRecentDrinkArrayList(insertMockData())
            setOnRecentDrinkItemClickListener(object: RecentDrinkAdapter.OnRecentDrinkItemClickListener {
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

            totalIntakeFromSplash = arguments?.getInt(RequestCode.TOTAL_TODAY_CAFFEINE_INTAKE_MAIN_TO_FRAGMENT, 0) ?: 0
            fragmentHomeDailyCaffeineIntakeValue.text = resources.getString(R.string.main_home_fragment_total_intake, totalIntakeFromSplash.toString())
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
                Logger.d("프랜차이즈 메뉴 사이즈 : ${result.list.size}")
                it.list.forEachIndexed { index, franchise ->
                    Logger.d("$index 번째 프랜차이즈 : ${franchise.franchiseName}")
                    Logger.d("[메뉴]")
                    franchise.menu.forEach { menu ->
                        Logger.d("$menu")
                    }
                }
            } ?: Logger.d("프랜차이즈 API call 실패")
        })
    }

    override fun initViewFinal() {
        mViewModel.getDecaffeineMenuList()

        // TODO : 프랜차이즈 메뉴 호출
        //mViewModel.getFranchiseMenuList()

        getFragmentBinding().fragmentHomeFrameLayout.setOnClickListener {
            startActivityForResult(Intent(activity?.applicationContext, HistoryTodayActivity::class.java).apply {
                putExtra(RequestCode.TODAY_CAFFEINE_INTAKE_MAIN_TO_HISTORY_REGISTER, totalIntakeFromSplash)
            }, RequestCode.HISTORY_TODAY_REQUEST_CODE)
        }

        getFragmentBinding().fragmentHomeTodayDecaffeineShowMoreButton.setOnClickListener {

            // TODO: 테스트 용도이므로 나중에 지워야됨
            //mDecaffeineArrayList.addAll(insertMockData())

            startActivity(Intent(activity?.applicationContext, TodayRecommendDrinkActivity::class.java).apply {
                putExtra(RequestCode.DECAFFEINE_TODAY_RECOMMEND_SHOW_MORE, mDecaffeineArrayList)
            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == RESULT_OK){
            when(requestCode){
                RequestCode.HISTORY_TODAY_REQUEST_CODE -> {
                    // TODO 히스토리 추가되어서 돌아오는 경우에 최근 마신음료 리스트, 일일 카페인 섭취량 갱신해야됨.
                    val refreshedValue:Int = data?.getIntExtra(RequestCode.TODAY_CAFFEINE_INTAKE_HISTORY_REGISTER_TO_MAIN, totalIntakeFromSplash) ?: totalIntakeFromSplash
                    getFragmentBinding().fragmentHomeDailyCaffeineIntakeValue.text = resources.getString(R.string.main_home_fragment_total_intake, refreshedValue.toString())
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