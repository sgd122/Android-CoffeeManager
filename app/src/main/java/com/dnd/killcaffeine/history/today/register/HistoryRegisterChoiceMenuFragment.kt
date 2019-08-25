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
import com.dnd.killcaffeine.model.data.menu.Menu
import com.dnd.killcaffeine.recyclerview.FranchiseMenuAdapter
import com.dnd.killcaffeine.recyclerview.decoration.SpacesItemDecoration
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

        getFragmentBinding().fragmentHistoryRegisterChoiceMenuRecyclerView.apply {
            layoutManager = GridLayoutManager(activity?.applicationContext, RECYCLER_VIEW_SPAN_COUNT)
            adapter = mFranchiseMenuAdapter
            addItemDecoration(SpacesItemDecoration(32))
        }

        // TODO 테스트 용도이므로 나중에 지워야함
        mFranchiseMenuAdapter.setFranchiseMenuArrayList(insertMockData())

        mFranchiseMenuAdapter.setItemOnClickListener(object: FranchiseMenuAdapter.OnFranchiseMenuClickListener {
            override fun onclick(menu: Menu) {
                activity?.let {
                    it.setResult(RESULT_OK, Intent().apply {
                        putExtra(RequestCode.HISTORY_REGISTER_SUCCESS_MENU, menu)
                    })
                    it.finish()
                }
            }
        })
    }

    override fun initDataBinding() {

    }

    override fun initViewFinal() {

    }

    // TODO 테스트 용도이므로 나중에 지워야함
    private fun insertMockData(): ArrayList<Menu> {
        return arrayListOf(
            Menu(1, "아이스 아메리카노", "R.drawable.coffee_sample", "스타벅스", 100, false),
            Menu(1, "아이스 아메리카노", "R.drawable.coffee_sample", "스타벅스", 100, false),
            Menu(1, "아이스 아메리카노", "R.drawable.coffee_sample", "스타벅스", 100, false),
            Menu(1, "아이스 아메리카노", "R.drawable.coffee_sample", "스타벅스", 100, false),
            Menu(1, "아이스 아메리카노", "R.drawable.coffee_sample", "스타벅스", 100, false),
            Menu(1, "아이스 아메리카노", "R.drawable.coffee_sample", "스타벅스", 100, false),
            Menu(1, "아이스 아메리카노", "R.drawable.coffee_sample", "스타벅스", 100, false),
            Menu(1, "아이스 아메리카노", "R.drawable.coffee_sample", "스타벅스", 100, false),
            Menu(1, "아이스 아메리카노", "R.drawable.coffee_sample", "스타벅스", 100, false),
            Menu(1, "아이스 아메리카노", "R.drawable.coffee_sample", "스타벅스", 100, false)
        )
    }
}