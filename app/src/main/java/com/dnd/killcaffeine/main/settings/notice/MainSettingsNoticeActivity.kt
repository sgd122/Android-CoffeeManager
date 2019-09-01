/*
 * Created by Lee Oh Hyoung on 2019-08-27..
 */
package com.dnd.killcaffeine.main.settings.notice

import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.base.BaseActivity
import com.dnd.killcaffeine.databinding.ActivityNoticeBinding
import com.dnd.killcaffeine.main.settings.notice.fragment.NoticeDetailFragment
import com.dnd.killcaffeine.main.settings.notice.fragment.NoticeListFragment
import com.dnd.killcaffeine.model.data.response.Notice
import kotlinx.android.synthetic.main.activity_notice.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainSettingsNoticeActivity : BaseActivity<ActivityNoticeBinding, MainSettingsNoticeViewModel>() {

    override val mViewModel: MainSettingsNoticeViewModel by viewModel()
    override val resourceId: Int
        get() = R.layout.activity_notice

    private val containerResId: Int = R.id.activity_notice_container

    override fun initViewStart() {

        supportFragmentManager.beginTransaction().add(containerResId, NoticeListFragment()).commitNow()

    }

    override fun initDataBinding() {
    }

    override fun initViewFinal() {
        activity_notice_back_button.setOnClickListener {
            if(supportFragmentManager.backStackEntryCount != 0){
                finish()
            } else {
                onBackPressed()
            }
        }
    }

    internal fun replaceDetailFragment(notice: Notice){
        supportFragmentManager.beginTransaction().run {
            replace(containerResId, NoticeDetailFragment(notice))
            addToBackStack(null)
            commit()
        }
    }
}