/*
 * Created by Lee Oh Hyoung on 2019-07-30 .. 
 */
package com.dnd.killcaffeine.main.home

import android.app.Activity.RESULT_OK
import android.content.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dnd.killcaffeine.BroadcastReceiverKey
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.RequestCode
import com.dnd.killcaffeine.base.BaseFragment
import com.dnd.killcaffeine.databinding.FragmentHomeBinding
import com.dnd.killcaffeine.dialog.ExceedRecommendWarningDialog
import com.dnd.killcaffeine.dialog.RecentDrinkDetailDialog
import com.dnd.killcaffeine.history.HistoryTodayActivity
import com.dnd.killcaffeine.recyclerview.DecaffeineAdpater
import com.dnd.killcaffeine.recyclerview.RecentDrinkAdapter
import com.dnd.killcaffeine.main.home.show_more.TodayRecommendDrinkActivity
import com.dnd.killcaffeine.model.data.room.menu.Menu
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

    override fun initViewStart() {

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

        fragment_home_today_decaffeine_recycler_view.run {
            layoutManager = LinearLayoutManager(activity?.applicationContext, RecyclerView.HORIZONTAL, false)
            adapter = mDecaffeineRecyclerViewAdapter
        }

        fragment_home_coffee_recent_recycler_view.run {
            layoutManager = LinearLayoutManager(activity?.applicationContext, RecyclerView.HORIZONTAL, false)
            adapter = mRecentRecyclerViewAdapter
        }

        fragment_home_daily_caffeine_intake_value.text = resources.getString(R.string.main_home_fragment_total_intake, mViewModel.getTotalCaffeineIntake().toString())
    }

    override fun initDataBinding() {
        mViewModel.decaffeineMenuLiveData.observe(this, Observer { result ->
            result?.let {
                mDecaffeineRecyclerViewAdapter.setDecaffeineArrayList(it.list)
                mDecaffeineArrayList.addAll(it.list)
            }
        })

        mViewModel.refreshedHistoryLiveData.observe(this, Observer { list ->
            list?.let {

                // 히스토리 내역이 변경되었다면, 리스트 갱신
                mRecentRecyclerViewAdapter.setRecentDrinkArrayList(it)

                mTotalCaffeineIntake = 0
                it.forEach { menu -> mTotalCaffeineIntake += menu.caffeine }
                getFragmentBinding().fragmentHomeDailyCaffeineIntakeValue.text = resources.getString(R.string.main_home_fragment_total_intake, mTotalCaffeineIntake.toString())

                mViewModel.checkExceedRecommendedQuantity(intake = mTotalCaffeineIntake)
            }
        })

        mViewModel.exceededIntakeLiveData.observe(this, Observer {
            // 권장섭취량 초과 했을때 경고 다이얼로그 띄움
            //showExceedWarningDialog()
        })

    }

    override fun initViewFinal() {
        mViewModel.getDecaffeineMenuList()

        fragment_home_frame_layout.setOnClickListener {
            val intent: Intent = Intent(activity?.applicationContext, HistoryTodayActivity::class.java).apply {
                putExtra(RequestCode.TODAY_CAFFEINE_INTAKE_MAIN_TO_HISTORY_REGISTER, mViewModel.getTotalCaffeineIntake())
            }

            startActivityForResult(intent, RequestCode.HISTORY_TODAY_REQUEST_CODE)
        }

        fragment_home_today_decaffeine_show_more_button.setOnClickListener {
            val intent: Intent = Intent(activity?.applicationContext, TodayRecommendDrinkActivity::class.java).apply {
                putExtra(RequestCode.DECAFFEINE_TODAY_RECOMMEND_SHOW_MORE, mDecaffeineArrayList)
            }

            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()

        mViewModel.refreshHistoryFromRoomDatabase()
        registerCommentReceiver()
        startCommentService()
    }

    override fun onStop() {
        super.onStop()

        unregisterCommentReceiver()
        stopCommentService()
    }

    private fun showRecentDrinkDetailDialog(menu: Menu){
        activity?.let {
            RecentDrinkDetailDialog(it, menu).show()
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