/*
 * Created by Lee Oh Hyoung on 2019-08-24..
 */
package com.dnd.killcaffeine.history

import android.app.Activity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.base.BaseActivity
import com.dnd.killcaffeine.databinding.ActivityTodayHistoryBinding
import com.dnd.killcaffeine.history.recyclerview.HistoryTodayAdapter
import com.dnd.killcaffeine.model.data.history.History
import com.dnd.killcaffeine.model.data.history.HistoryDatabase
import kotlinx.android.synthetic.main.activity_today_history.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryTodayActivity : BaseActivity<ActivityTodayHistoryBinding, HistoryTodayViewModel>() {

    override val mViewModel: HistoryTodayViewModel by viewModel()
    override val resourceId: Int
        get() = R.layout.activity_today_history

    private val mHistoryTodayAdapter: HistoryTodayAdapter by inject()
    private val mHistoryDatabase: HistoryDatabase by inject()

    override fun initViewStart() {
        activity_today_history_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@HistoryTodayActivity, RecyclerView.VERTICAL, false)
            adapter = mHistoryTodayAdapter
        }
    }

    override fun initDataBinding() {
        mViewModel.historyListLiveData.observe(this, Observer { list ->
            list?.let {
                when(it.size){
                    0 -> mViewModel.insertHistoryListToRoomDatabase(insertMockHistory() as List<History>, mHistoryDatabase)
                    else -> mHistoryTodayAdapter.setHistoryList(it)
                }
            }
        })

        mViewModel.insertHistoryLiveData.observe(this, Observer {
            mViewModel.loadHistoryListFromRoomDatabase(mHistoryDatabase)
        })

        mViewModel.failureHistoryLiveData.observe(this, Observer {
            mHistoryTodayAdapter.setHistoryList(insertMockHistory())
        })
    }

    override fun initViewFinal() {
        mViewModel.loadHistoryListFromRoomDatabase(mHistoryDatabase)

        activity_today_history_back_button.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    private fun insertMockHistory(): ArrayList<History> {
        return arrayListOf(
            History(0, "아이스 아메리카노", "스타벅스", 150),
            History(0, "카페 라떼", "이디야", 100),
            History(0, "에스프레소", "빽다방", 180)
        )
    }

    /*History(0, "카푸치노", "엔젤리너스", 120),
            History(0, "자바칩 프라푸치노", "스타벅스", 100),
            History(0, "바닐라 라떼", "더벤티", 120),
            History(0, "디카페인 아메리카노", "탐앤탐스", 30),
            History(0, "얼그레이 버블티", "공차", 50),
            History(0, "아이스 아메리카노", "스타벅스", 150),
            History(0, "카페 라떼", "이디야", 100),
            History(0, "에스프레소", "빽다방", 180),
            History(0, "카푸치노", "엔젤리너스", 120),
            History(0, "자바칩 프라푸치노", "스타벅스", 100),
            History(0, "바닐라 라떼", "더벤티", 120),
            History(0, "디카페인 아메리카노", "탐앤탐스", 30),
            History(0, "얼그레이 버블티", "공차", 50)*/
}