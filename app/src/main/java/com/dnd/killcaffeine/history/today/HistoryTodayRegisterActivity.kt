/*
 * Created by Lee Oh Hyoung on 2019-08-25..
 */
package com.dnd.killcaffeine.history.today

import androidx.fragment.app.Fragment
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.base.BaseActivity
import com.dnd.killcaffeine.databinding.ActivityHistoryTodayRegisterBinding
import com.dnd.killcaffeine.history.today.register.HistoryRegisterChoiceFranchiseFragment
import com.dnd.killcaffeine.history.today.register.HistoryRegisterChoiceMenuFragment
import com.dnd.killcaffeine.history.today.register.HistoryRegisterChoiceTypeFragment
import com.dnd.killcaffeine.model.data.room.menu.Menu
import kotlinx.android.synthetic.main.activity_history_today_register.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryTodayRegisterActivity : BaseActivity<ActivityHistoryTodayRegisterBinding, HistoryTodayRegisterViewModel>() {

    override val mViewModel: HistoryTodayRegisterViewModel by viewModel()
    override val resourceId: Int
        get() = R.layout.activity_history_today_register

    private val containerResId: Int = R.id.activity_history_today_register_container

    override fun initViewStart() {
        supportFragmentManager.beginTransaction().add(
            containerResId,
            getFragment(RegisterComponent.Type.ordinal)
        ).commitNow()
    }

    override fun initDataBinding() {

    }

    override fun initViewFinal() {
        activity_history_today_back_button.setOnClickListener {
            finish()
        }
    }

    internal fun replaceFranchiseFragment(){
        supportFragmentManager.beginTransaction().run {
            replace(containerResId, getFragment(RegisterComponent.Franchise.ordinal))
            addToBackStack(null)
            commit()
        }
    }

    internal fun replaceMenuFragment(){
        supportFragmentManager.beginTransaction().run {
            replace(containerResId, getFragment(RegisterComponent.Menu.ordinal))
            addToBackStack(null)
            commit()
        }
    }

    companion object {
        var chosenFranchiseMenu: ArrayList<Menu> = ArrayList()

        enum class RegisterComponent {
            Type, Franchise, Menu
        }

        private val fragmentList = listOf(
            HistoryRegisterChoiceTypeFragment.newInstance(),
            HistoryRegisterChoiceFranchiseFragment.newInstance(),
            HistoryRegisterChoiceMenuFragment.newInstance()
        )

        fun getFragment(position: Int): Fragment = fragmentList[position]
    }
}