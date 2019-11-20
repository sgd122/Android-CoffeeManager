/*
 * Created by Lee Oh Hyoung on 2019-07-30 .. 
 */
package com.dnd.killcaffeine.main.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dnd.killcaffeine.BroadcastReceiverKey
import com.dnd.killcaffeine.Constants
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.RequestCode
import com.dnd.killcaffeine.base.BaseFragment
import com.dnd.killcaffeine.databinding.FragmentHomeBinding
import com.dnd.killcaffeine.dialog.ExceedRecommendWarningDialog
import com.dnd.killcaffeine.dialog.RecentDrinkDetailDialog
import com.dnd.killcaffeine.history.HistoryTodayActivity
import com.dnd.killcaffeine.main.MainActivity
import com.dnd.killcaffeine.main.home.show_more.TodayRecommendDrinkActivity
import com.dnd.killcaffeine.model.data.room.menu.Menu
import com.dnd.killcaffeine.recyclerview.DecaffeineAdpater
import com.dnd.killcaffeine.recyclerview.RecentDrinkAdapter
import com.dnd.killcaffeine.service.CommentService
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainHomeFragment : BaseFragment<FragmentHomeBinding, MainHomeViewModel>() {

    override val mViewModel: MainHomeViewModel by viewModel()
    override val resourceId: Int
        get() = R.layout.fragment_home

    private val mDecaffeineRecyclerViewAdapter: DecaffeineAdpater by inject()
    private val mRecentRecyclerViewAdapter: RecentDrinkAdapter by inject()

    private val mDecaffeineArrayList: ArrayList<Menu> = ArrayList()

    private var mCommentReceiver: BroadcastReceiver? = null

    private var mTotalCaffeineIntake: Int = 0
    private var mPersonalRecommendCaffeine = 0

    override fun initViewStart() {

        Logger.d("퍼센트, savedCaffeineIntake : ${MainActivity.savedCaffeineIntake}\nsavedPersonalRecommend: ${MainActivity.savedPersonalRecommend}")

        mRecentRecyclerViewAdapter.apply {
            setRecentDrinkArrayList(insertMockData())
            setOnRecentDrinkItemClickListener(object: RecentDrinkAdapter.OnRecentDrinkItemClickListener {
                override fun onItemClick(menu: Menu) {
                    showRecentDrinkDetailDialog(menu)
                }
            })
        }

        fragment_home_today_decaffeine_recycler_view.run {
            layoutManager = LinearLayoutManager(activity?.applicationContext, RecyclerView.HORIZONTAL, false)
            adapter = mDecaffeineRecyclerViewAdapter
        }

        // 최근 마신 음
        fragment_home_coffee_recent_recycler_view.run {
            layoutManager = LinearLayoutManager(activity?.applicationContext, RecyclerView.HORIZONTAL, false)
            adapter = mRecentRecyclerViewAdapter
        }
    }

    override fun initDataBinding() {
        mViewModel.decaffeineLiveData.observe(this, Observer { list ->
            list?.let {
                mDecaffeineRecyclerViewAdapter.setDecaffeineArrayList(it.shuffled().take(10)) // 홈화면에서는 10개만.. 더보기에서는 30개 전부
                mDecaffeineArrayList.addAll(it)

            } ?: insertMockData().let {
                mDecaffeineRecyclerViewAdapter.setDecaffeineArrayList(it)
                mDecaffeineArrayList.addAll(it)
            }
        })

        // 최근 마신 음료 Observe
        mViewModel.refreshedHistoryLiveData.observe(this, Observer { list ->
            list?.let {


                // 히스토리 내역이 변경되었다면, 리스트 갱신
                mRecentRecyclerViewAdapter.setRecentDrinkArrayList(it)

                mTotalCaffeineIntake = 0
                it.forEach { menu -> mTotalCaffeineIntake += menu.caffeine }

                MainActivity.savedCaffeineIntake = mTotalCaffeineIntake
                setupBottleContent(MainActivity.savedCaffeineIntake, MainActivity.savedPersonalRecommend)

                getFragmentBinding().fragmentHomeDailyCaffeineIntakeValue.text = mTotalCaffeineIntake.toString()

                //mViewModel.checkExceedRecommendedQuantity(intake = mTotalCaffeineIntake)
            }
        })

        mViewModel.exceededIntakeLiveData.observe(this, Observer {
            // 권장섭취량 초과 했을때 경고 다이얼로그 띄움
            //showExceedWarningDialog()
        })

        mViewModel.savedPersonalRecommand.observe(this, Observer { recommend ->
            MainActivity.savedPersonalRecommend = recommend
            fragment_home_personal_recommend_caffeine.text = getString(R.string.main_home_fragment_personal_recommend_caffeine, recommend.toString())
        })

        mViewModel.recentRegisterLiveData.observe(this, Observer {
            mViewModel.refreshHistoryFromRoomDatabase()
        })

        mViewModel.personalLiveDataValid.observe(this, Observer { isValid ->
            fragment_home_personal_not_set.visibility = when(isValid) {
                true -> {
                    setupBottleContent(MainActivity.savedCaffeineIntake, MainActivity.savedPersonalRecommend)
                    View.GONE
                } // 저장되어 있다면, 안보임
                false -> View.VISIBLE
            }
        })

    }

    override fun initViewFinal() {

        mViewModel.getPersonalRecommendCaffeine()
        mViewModel.recommendDecaffeineMenus()

        fragment_home_frame_layout.setOnClickListener {

            startActivity(Intent(activity?.applicationContext, HistoryTodayActivity::class.java))
        }

        fragment_home_today_decaffeine_show_more_button.setOnClickListener {
            val intent: Intent = Intent(activity?.applicationContext, TodayRecommendDrinkActivity::class.java).apply {
                putExtra(RequestCode.DECAFFEINE_TODAY_RECOMMEND_SHOW_MORE, mDecaffeineArrayList)
            }

            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        mViewModel.checkPersonalCaffeineSaved() // 홈 화면 되돌아올 때 마다 마이카페인이 저장되어 있는 지 확인

        mViewModel.refreshHistoryFromRoomDatabase()
        //setupBottleContent(MainActivity.savedCaffeineIntake, MainActivity.savedPersonalRecommend)
        registerCommentReceiver()
        startCommentService()
    }

    override fun onPause() {
        super.onPause()

        unregisterCommentReceiver()
        stopCommentService()
    }

    private fun showRecentDrinkDetailDialog(menu: Menu){
        activity?.let {
            Log.d("최근마신음료", menu.toString())
            if(menu.menuId != Constants.COFFEE_NOT_FOUND) {
                RecentDrinkDetailDialog(it, menu, object: RecentDrinkDetailDialog.OnRecentDrinkRegisterListener {
                    override fun onRecentDrinkRegister(menu: Menu) {
                        menu.run {
                            val newMenu = Menu(
                                menuName = menuName,
                                menuImgUrl = menuImgUrl,
                                caffeine = caffeine,
                                franchiseName = franchiseName,
                                personalShop = personalShop
                            )

                            mViewModel.insertHistoryToRoomDatabase(menu = newMenu)
                        }
                    }
                }).show()
            }
        }
    }

    private fun showExceedWarningDialog(){
        activity?.let {
            ExceedRecommendWarningDialog(it).show()
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

    private fun registerCommentReceiver(){
        mCommentReceiver = object: BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                intent?.getStringExtra(BroadcastReceiverKey.COMMENT_SERVICE_RANDOM)?.let { comment ->
                    getFragmentBinding().fragmentHomeTodayCommentTextView.text = comment
                }
            }
        }

        activity?.registerReceiver(mCommentReceiver, IntentFilter(BroadcastReceiverKey.COMMENT_SERVICE_BROADCAST))
    }

    private fun unregisterCommentReceiver(){
        mCommentReceiver?.let { receiver ->
            activity?.unregisterReceiver(receiver)
        }
    }

    private fun setupBottleContent(intake: Int = 0, recommend: Int = 0){
        val percentage: Double = mViewModel.calCaffeinePercentage(
            intake = intake,
            recommend = recommend
        )
        Logger.d("퍼센트 : $percentage")
        when {
            percentage <= 30.0 -> fragment_home_coffee_bottle_content.setImageDrawable(activity?.getDrawable(R.drawable.background_bottle_content_normal))
            percentage <= 80.0 -> fragment_home_coffee_bottle_content.setImageDrawable(activity?.getDrawable(R.drawable.background_bottle_content_middle))
            else -> fragment_home_coffee_bottle_content.setImageDrawable(activity?.getDrawable(R.drawable.background_bottle_content_exceed))
        }
    }

    private fun insertMockData(): ArrayList<Menu> {
        return arrayListOf(
            Menu( "아이스 아메리카노","스타벅스", 100, R.drawable.coffee_sample),
            Menu( "아이스 아메리카노","스타벅스", 100, R.drawable.coffee_sample),
            Menu( "아이스 아메리카노","스타벅스", 100, R.drawable.coffee_sample),
            Menu( "아이스 아메리카노","스타벅스", 100, R.drawable.coffee_sample),
            Menu( "아이스 아메리카노","스타벅스", 100, R.drawable.coffee_sample),
            Menu( "아이스 아메리카노","스타벅스", 100, R.drawable.coffee_sample),
            Menu( "아이스 아메리카노","스타벅스", 100, R.drawable.coffee_sample),
            Menu( "아이스 아메리카노","스타벅스", 100, R.drawable.coffee_sample)
        )
    }
}