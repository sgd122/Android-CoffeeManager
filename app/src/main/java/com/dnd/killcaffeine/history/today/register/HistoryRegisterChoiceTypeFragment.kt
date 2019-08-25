/*
 * Created by Lee Oh Hyoung on 2019-08-25..
 */
package com.dnd.killcaffeine.history.today.register

import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.base.BaseFragment
import com.dnd.killcaffeine.databinding.FragmentHistoryRegisterChoiceTypeBinding
import com.dnd.killcaffeine.history.today.HistoryTodayRegisterActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryRegisterChoiceTypeFragment : BaseFragment<FragmentHistoryRegisterChoiceTypeBinding, HistoryRegisterChoiceTypeViewModel>() {

    companion object {
        fun newInstance() = HistoryRegisterChoiceTypeFragment()
    }

    override val mViewModel: HistoryRegisterChoiceTypeViewModel by viewModel()
    override val resourceId: Int
        get() = R.layout.fragment_history_register_choice_type


    override fun initViewStart() {

    }

    override fun initDataBinding() {

    }

    override fun initViewFinal() {
        getFragmentBinding().fragmentHistoryChoiceTypeCoffee.setOnClickListener {

            (activity as? HistoryTodayRegisterActivity)?.replaceFranchiseFragment()
        }

        getFragmentBinding().fragmentHistoryChoiceTypeDrink.setOnClickListener {
            showSnackbar("일반음료 선택")
        }

    }
}