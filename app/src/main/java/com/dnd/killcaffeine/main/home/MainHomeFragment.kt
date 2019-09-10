/*
 * Created by Lee Oh Hyoung on 2019-07-30 .. 
 */
package com.dnd.killcaffeine.main.home

import android.app.Activity.RESULT_OK
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.RequestCode
import com.dnd.killcaffeine.base.BaseFragment
import com.dnd.killcaffeine.databinding.FragmentHomeBinding
import com.dnd.killcaffeine.dialog.RecentDrinkDetailDialog
import com.dnd.killcaffeine.history.HistoryTodayActivity
import com.dnd.killcaffeine.recyclerview.DecaffeineAdpater
import com.dnd.killcaffeine.recyclerview.RecentDrinkAdapter
import com.dnd.killcaffeine.main.home.show_more.TodayRecommendDrinkActivity
import com.dnd.killcaffeine.model.data.room.menu.Menu
import com.dnd.killcaffeine.service.CommentService
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

    private var mCommentService: CommentService? = null
    private var mCommentServiceConnection: ServiceConnection? = null
    private var mCommentServiceBounded = false

    override fun initViewStart() {


        (arguments?.getInt(RequestCode.TOTAL_TODAY_CAFFEINE_INTAKE_MAIN_TO_FRAGMENT, 0) ?: 0).run {
            mViewModel.setTotalCaffeineIntake(this)
        }
        (arguments?.getSerializable(RequestCode.TOTAL_TODAY_MENU_LIST_MAIN_TO_FRAGMENT) as ArrayList<Menu>).run {
            mViewModel.setSavedMenuList(this)
        }


        mDecaffeineRecyclerViewAdapter.apply {
            setDecaffeineArrayList(insertMockData())
        }

        mRecentRecyclerViewAdapter.apply {
            setRecentDrinkArrayList(insertMockData())
            setOnRecentDrinkItemClickListener(object: RecentDrinkAdapter.OnRecentDrinkItemClickListener {
                override fun onItemClick(menu: Menu) {
                    showRecentDrinkDetailDialog(menu)
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

            fragmentHomeDailyCaffeineIntakeValue.text = resources.getString(R.string.main_home_fragment_total_intake, mViewModel.getTotalCaffeineIntake().toString())
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
                    mViewModel.getSavedMenuList()?.let { menuList ->
                        mRecentRecyclerViewAdapter.setRecentDrinkArrayList(menuList)
                    }
                }
            }
        })

        mViewModel.refreshedHistoryLiveData.observe(this, Observer { list ->
            list?.let {

               // 히스토리 내역이 변경되었다면, 리스트 갱신
                mViewModel.setSavedMenuList(list)
                mRecentRecyclerViewAdapter.setRecentDrinkArrayList(list)
            }

        })
    }

    override fun initViewFinal() {
        bindCommentService()

        mViewModel.getDecaffeineMenuList()

        getFragmentBinding().fragmentHomeFrameLayout.setOnClickListener {
            startActivityForResult(Intent(activity?.applicationContext, HistoryTodayActivity::class.java).apply {
                putExtra(RequestCode.TODAY_CAFFEINE_INTAKE_MAIN_TO_HISTORY_REGISTER, mViewModel.getTotalCaffeineIntake())
            }, RequestCode.HISTORY_TODAY_REQUEST_CODE)
        }

        getFragmentBinding().fragmentHomeTodayDecaffeineShowMoreButton.setOnClickListener {

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
                    val refreshedValue:Int = data?.getIntExtra(RequestCode.TODAY_CAFFEINE_INTAKE_HISTORY_REGISTER_TO_MAIN, mViewModel.getTotalCaffeineIntake()) ?: mViewModel.getTotalCaffeineIntake()

                    mViewModel.refreshHistoryFromRoomDatabase()
                    getFragmentBinding().fragmentHomeDailyCaffeineIntakeValue.text = resources.getString(R.string.main_home_fragment_total_intake, refreshedValue.toString())
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()

        Logger.d("HomeFragment 의 onStart 에서 mCommentService: $mCommentService")
        mCommentService?.let { service ->
            Logger.d("MainHomeFragment에서의 TodayComment: ${service.getRandomComment()}")
        }

        if(mCommentServiceBounded){
            mCommentService?.let { service ->
                Logger.d("MainHomeFragment에서의 TodayComment: ${service.getRandomComment()}")
            }
        }
    }

    override fun onStop() {
        super.onStop()

        unbindCommentService()
    }

    override fun onDestroy() {
        super.onDestroy()

        unbindCommentService()

    }

    private fun showRecentDrinkDetailDialog(menu: Menu){
        activity?.let {
            RecentDrinkDetailDialog(it, menu).show()
        }
    }

    private fun startCommentService(){
        activity?.run {
            startService(Intent(applicationContext, CommentService::class.java))
        }
    }

    private fun stopCommentService(){
        activity?.run {
            stopService(Intent(applicationContext, CommentService::class.java))
        }
    }

    private fun setupCommentServiceConnection(): ServiceConnection {
        return object: ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                val mBinder: CommentService.LocalBinder? = service as? CommentService.LocalBinder
                mCommentService = mBinder?.getService()
                mCommentServiceBounded = true
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                mCommentServiceBounded = false
            }
        }
    }

    private fun bindCommentService(){
        activity?.run {
            val intent = Intent(applicationContext, CommentService::class.java)
            mCommentServiceConnection = setupCommentServiceConnection()

            mCommentServiceConnection?.let { conn ->
                bindService(intent, conn, Context.BIND_AUTO_CREATE) // 코멘트 서비스 바인딩
            }
        }
    }

    private fun unbindCommentService(){
        if(mCommentServiceBounded && mCommentServiceConnection != null){
            activity?.unbindService(mCommentServiceConnection!!)
            mCommentServiceBounded = false
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