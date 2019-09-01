/*
 * Created by Lee Oh Hyoung on 2019-08-27..
 */
package com.dnd.killcaffeine.main.settings.notice.fragment

import androidx.lifecycle.Observer
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.base.BaseFragment
import com.dnd.killcaffeine.databinding.FragmentNoticeDetailBinding
import com.dnd.killcaffeine.model.data.response.Notice
import com.orhanobut.logger.Logger
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoticeDetailFragment(private val notice: Notice) : BaseFragment<FragmentNoticeDetailBinding, NoticeDetailViewModel>() {

    override val mViewModel: NoticeDetailViewModel by viewModel()
    override val resourceId: Int
        get() = R.layout.fragment_notice_detail

    override fun initViewStart() {
    }

    override fun initDataBinding() {
        mViewModel.noticeDetailLiveData.observe(this, Observer { detail ->
            detail?.let {
                getFragmentBinding().fragmentNoticeDetailTextView.text = it
            }
        })

        mViewModel.exceptionNoticeLiveData.observe(this, Observer {
            // 서버 통신 실패시 예외처리
            getFragmentBinding().run {
                fragmentNoticeDetailWriteTimeTextView.text = resources.getString(R.string.list_item_notice_write_time_example)
                fragmentNoticeDetailTitleTextView.text = resources.getString(R.string.list_item_notice_title_example)
                fragmentNoticeDetailTextView.text = resources.getString(R.string.list_item_notice_detail_example)
            }
        })
    }

    override fun initViewFinal() {
        getFragmentBinding().run {
            fragmentNoticeDetailWriteTimeTextView.text = notice.writeTime
            fragmentNoticeDetailTitleTextView.text = notice.title
        }

        mViewModel.getNoticeDetail(notice.noticeId)
    }
}