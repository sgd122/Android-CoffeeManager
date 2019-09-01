/*
 * Created by Lee Oh Hyoung on 2019-08-24..
 */
package com.dnd.killcaffeine.history

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.RequestCode
import com.dnd.killcaffeine.base.BaseActivity
import com.dnd.killcaffeine.databinding.ActivityHistoryTodayBinding
import com.dnd.killcaffeine.history.today.HistoryTodayRegisterActivity
import com.dnd.killcaffeine.model.data.history.History
import com.dnd.killcaffeine.model.data.menu.Menu
import com.dnd.killcaffeine.recyclerview.HistoryTodayAdapter
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_history_today.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryTodayActivity : BaseActivity<ActivityHistoryTodayBinding, HistoryTodayViewModel>() {

    private val TAG = "HistoryTodayActivity"

    override val mViewModel: HistoryTodayViewModel by viewModel()
    override val resourceId: Int
        get() = R.layout.activity_history_today

    private val mHistoryTodayAdapter: HistoryTodayAdapter by inject()

    private var mTodayCaffeineFromMainFragment: Int = 0
    private var mTodayCaffeineIntakeCalculation: Int = 0

    override fun initViewStart() {
        if(intent.hasExtra(RequestCode.TODAY_CAFFEINE_INTAKE_MAIN_TO_HISTORY_REGISTER)){
            mTodayCaffeineFromMainFragment = intent.getIntExtra(RequestCode.TODAY_CAFFEINE_INTAKE_MAIN_TO_HISTORY_REGISTER, 0)
            Logger.d("홈프레그먼트에서 받아온 카페인 : $mTodayCaffeineFromMainFragment")
        }

        activity_today_history_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@HistoryTodayActivity, RecyclerView.VERTICAL, false)
            adapter = mHistoryTodayAdapter
        }
    }

    override fun initDataBinding() {
        mViewModel.historyListLiveData.observe(this, Observer { list ->
            list?.let {
                mHistoryTodayAdapter.setHistoryList(it)
                mTodayCaffeineIntakeCalculation = 0
                list.forEach { history ->
                    mTodayCaffeineIntakeCalculation += history.caffeine
                }
                Logger.d("새롭게 계산한 카페인 : $mTodayCaffeineIntakeCalculation")
            }
        })

        mViewModel.insertHistoryLiveData.observe(this, Observer {
            mViewModel.loadHistoryListFromRoomDatabase()
        })

        mViewModel.failureHistoryLiveData.observe(this, Observer {
            mHistoryTodayAdapter.setHistoryList(insertMockHistory())
        })
    }

    override fun initViewFinal() {
        mViewModel.loadHistoryListFromRoomDatabase()

        activity_today_history_back_button.setOnClickListener {
            transferDataWhenRegister()
            finish()
        }

        activity_today_history_floating_action_button.setOnClickListener {
            startActivityForResult(Intent(applicationContext, HistoryTodayRegisterActivity::class.java), RequestCode.HISTORY_REGISTER_REQUEST_CODE)
        }
    }

    override fun finish() {
        transferDataWhenRegister()
        super.finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                RequestCode.HISTORY_REGISTER_REQUEST_CODE -> {
                    val menu: Menu? = data?.getSerializableExtra(RequestCode.HISTORY_REGISTER_SUCCESS_MENU) as? Menu
                    menu?.let {
                        Logger.d(it.toString())

                        History(0, it.menuName, it.franchiseName, it.caffeine).run {
                            mHistoryTodayAdapter.addHistory(this)
                            mViewModel.insertHistoryToRoomDatabase(this)
                        }
                    }
                }
            }
        }
    }

    private fun transferDataWhenRegister(){
        when(mTodayCaffeineFromMainFragment != mTodayCaffeineIntakeCalculation){
            true -> {
                setResult(Activity.RESULT_OK, Intent().apply {
                    putExtra(RequestCode.TODAY_CAFFEINE_INTAKE_HISTORY_REGISTER_TO_MAIN, mTodayCaffeineIntakeCalculation)
                })
            }
            false -> setResult(Activity.RESULT_OK)
        }
    }

    private fun insertMockHistory(): ArrayList<History> {
        return arrayListOf(
            History(0, "아이스 아메리카노", "스타벅스", 150),
            History(0, "카페 라떼", "이디야", 100),
            History(0, "에스프레소", "빽다방", 180),
            History(0, "카푸치노", "엔젤리너스", 120),
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
            History(0, "얼그레이 버블티", "공차", 50)
        )
    }
}