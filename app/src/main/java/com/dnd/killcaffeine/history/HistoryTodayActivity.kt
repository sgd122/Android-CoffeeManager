/*
 * Created by Lee Oh Hyoung on 2019-08-24..
 */
package com.dnd.killcaffeine.history

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.RequestCode
import com.dnd.killcaffeine.base.BaseActivity
import com.dnd.killcaffeine.databinding.ActivityHistoryTodayBinding
import com.dnd.killcaffeine.dialog.HistoryDeleteWarningDialog
import com.dnd.killcaffeine.history.today.HistoryTodayRegisterActivity
import com.dnd.killcaffeine.model.data.room.menu.Menu
import com.dnd.killcaffeine.recyclerview.HistoryTodayAdapter
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_history_today.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryTodayActivity : BaseActivity<ActivityHistoryTodayBinding, HistoryTodayViewModel>() {

    override val mViewModel: HistoryTodayViewModel by viewModel()
    override val resourceId: Int
        get() = R.layout.activity_history_today

    private val mHistoryTodayAdapter: HistoryTodayAdapter by inject()

    override fun initViewStart() {
        mHistoryTodayAdapter.setOnHistoryClickListener(object: HistoryTodayAdapter.OnHistoryClickListener{
            override fun onClick(menu: Menu) {

                // 아이템 클릭하면 다이얼로그 띄우기
                showHistoryDeleteDialog(menu)
            }
        })

        activity_today_history_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@HistoryTodayActivity, RecyclerView.VERTICAL, false)
            adapter = mHistoryTodayAdapter
        }
    }

    override fun initDataBinding() {
        mViewModel.historyListLiveData.observe(this, Observer { list ->
            list?.let {
                mHistoryTodayAdapter.setHistoryList(it)
            }
        })

        mViewModel.insertHistoryLiveData.observe(this, Observer {
            mViewModel.loadHistoryListFromRoomDatabase()
        })

        mViewModel.deleteHistoryLiveData.observe(this, Observer { history ->
            mHistoryTodayAdapter.deleteHistory(history)
        })
    }

    override fun initViewFinal() {
        mViewModel.loadHistoryListFromRoomDatabase()

        activity_today_history_back_button.setOnClickListener {
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
                    val menu: Menu? = data?.getSerializableExtra(RequestCode.HISTORY_REGISTER_SUCCESS_MENU) as? Menu
                    menu?.let {

                        mHistoryTodayAdapter.addHistory(it)
                        mViewModel.insertHistoryToRoomDatabase(it)
                    }
                }
            }
        }
    }

    private fun showHistoryDeleteDialog(menu: Menu){
        HistoryDeleteWarningDialog(this, View.OnClickListener {

            mViewModel.deleteHistoryFromRoomDatabase(menu)

        }).show()
    }
}