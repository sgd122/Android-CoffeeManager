/*
 * Created by Lee Oh Hyoung on 2019-08-25..
 */
package com.dnd.killcaffeine.history.today.register

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.RequestCode
import com.dnd.killcaffeine.base.BaseFragment
import com.dnd.killcaffeine.databinding.FragmentHistoryRegisterChoiceMenuBinding
import com.dnd.killcaffeine.dialog.HistoryRegisterWarningDialog
import com.dnd.killcaffeine.history.today.HistoryTodayRegisterActivity
import com.dnd.killcaffeine.model.data.room.menu.Menu
import com.dnd.killcaffeine.recyclerview.FranchiseMenuAdapter
import com.dnd.killcaffeine.recyclerview.decoration.SpacesItemDecoration
import kotlinx.android.synthetic.main.fragment_history_register_choice_menu.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryRegisterChoiceMenuFragment : BaseFragment<FragmentHistoryRegisterChoiceMenuBinding, HistoryRegisterChoiceMenuViewModel>() {

    companion object {
        fun newInstance() = HistoryRegisterChoiceMenuFragment()

        const val RECYCLER_VIEW_SPAN_COUNT: Int = 2
    }
    override val resourceId: Int
        get() = R.layout.fragment_history_register_choice_menu

    override val mViewModel: HistoryRegisterChoiceMenuViewModel by viewModel()
    private val mFranchiseMenuAdapter: FranchiseMenuAdapter by inject()

    override fun initViewStart() {

        fragment_history_register_choice_menu_recycler_view.apply {
            layoutManager = GridLayoutManager(activity?.applicationContext, RECYCLER_VIEW_SPAN_COUNT)
            adapter = mFranchiseMenuAdapter.apply {
                setFranchiseMenuArrayList(HistoryTodayRegisterActivity.chosenFranchiseMenu)
                setItemOnClickListener(object: FranchiseMenuAdapter.OnFranchiseMenuClickListener {
                    override fun onclick(menu: Menu) {
                        showWarningDialog(menu)
                    }
                })
            }
        }
    }

    override fun initDataBinding() {

    }

    override fun initViewFinal() {

    }

    private fun showWarningDialog(menu: Menu){
        activity?.let { activity ->
            HistoryRegisterWarningDialog(activity, View.OnClickListener {
                activity.setResult(RESULT_OK, Intent().apply {
                    putExtra(RequestCode.HISTORY_REGISTER_SUCCESS_MENU, menu)
                })
                activity.finish()
            }).show()
        }
    }

    // TODO 테스트 용도이므로 나중에 지워야함
    private fun insertMockData(): ArrayList<Menu> {
        return arrayListOf(
            Menu("아이스 아메리카노","스타벅스", 100, R.drawable.coffee_sample),
            Menu("아이스 아메리카노","스타벅스", 100, R.drawable.coffee_sample),
            Menu("아이스 아메리카노","스타벅스", 100, R.drawable.coffee_sample),
            Menu("아이스 아메리카노","스타벅스", 100, R.drawable.coffee_sample),
            Menu("아이스 아메리카노","스타벅스", 100, R.drawable.coffee_sample),
            Menu("아이스 아메리카노","스타벅스", 100, R.drawable.coffee_sample),
            Menu("아이스 아메리카노","스타벅스", 100, R.drawable.coffee_sample),
            Menu("아이스 아메리카노","스타벅스", 100, R.drawable.coffee_sample),
            Menu("아이스 아메리카노","스타벅스", 100, R.drawable.coffee_sample),
            Menu("아이스 아메리카노","스타벅스", 100, R.drawable.coffee_sample)
        )

    }
}