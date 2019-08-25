/*
 * Created by Lee Oh Hyoung on 2019-08-24..
 */
package com.dnd.killcaffeine.history

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.RequestCode
import com.dnd.killcaffeine.base.BaseActivity
import com.dnd.killcaffeine.databinding.ActivityHistoryTodayBinding
import com.dnd.killcaffeine.recyclerview.HistoryTodayAdapter
import com.dnd.killcaffeine.history.today.HistoryTodayRegisterActivity
import com.dnd.killcaffeine.model.data.history.History
import com.dnd.killcaffeine.model.data.history.HistoryDatabase
import kotlinx.android.synthetic.main.activity_history_today.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryTodayActivity : BaseActivity<ActivityHistoryTodayBinding, HistoryTodayViewModel>() {

    private val TAG = "HistoryTodayActivity"

    override val mViewModel: HistoryTodayViewModel by viewModel()
    override val resourceId: Int
        get() = R.layout.activity_history_today

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

        activity_today_history_floating_action_button.setOnClickListener {
            startActivityForResult(Intent(applicationContext, HistoryTodayRegisterActivity::class.java), RequestCode.HISTORY_REGISTER_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                RequestCode.HISTORY_REGISTER_REQUEST_CODE -> {
                    val message = data?.getStringExtra("TEXT") ?: ""
                    Log.d(TAG, message)
                }
            }
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