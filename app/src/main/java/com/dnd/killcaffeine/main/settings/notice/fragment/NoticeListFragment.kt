/*
 * Created by Lee Oh Hyoung on 2019-08-27..
 */
package com.dnd.killcaffeine.main.settings.notice.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.base.BaseFragment
import com.dnd.killcaffeine.databinding.FragmentNoticeListBinding
import com.dnd.killcaffeine.main.settings.notice.MainSettingsNoticeActivity
import com.dnd.killcaffeine.model.data.response.Notice
import com.dnd.killcaffeine.recyclerview.NoticeAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoticeListFragment : BaseFragment<FragmentNoticeListBinding, NoticeListViewModel>() {

    override val mViewModel: NoticeListViewModel by viewModel()
    override val resourceId: Int
        get() = R.layout.fragment_notice_list

    private val mNoticeAdapter: NoticeAdapter by inject()

    override fun initViewStart() {
        mNoticeAdapter.setOnNoticeClickListener(object: NoticeAdapter.OnNoticeClickListener {
            override fun onClick(notice: Notice) {
                val parentActivity = activity as? MainSettingsNoticeActivity
                parentActivity?.replaceDetailFragment(notice)
            }
        })

        getFragmentBinding().fragmentNoticeListRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity?.applicationContext, RecyclerView.VERTICAL, false)
            adapter = mNoticeAdapter
        }
    }

    override fun initDataBinding() {
        mViewModel.noticeListLiveData.observe(this, Observer { list ->
            list?.let {
                mNoticeAdapter.setNoticeList(it)
            }
        })

    }

    override fun initViewFinal() {
        mViewModel.getNoticeList()
    }
}