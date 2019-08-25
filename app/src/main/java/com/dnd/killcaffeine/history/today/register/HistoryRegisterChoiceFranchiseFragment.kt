/*
 * Created by Lee Oh Hyoung on 2019-08-25..
 */
package com.dnd.killcaffeine.history.today.register

import coil.api.load
import coil.transform.CircleCropTransformation
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.base.BaseFragment
import com.dnd.killcaffeine.databinding.FragmentHistoryRegisterChoiceFranchiseBinding
import com.dnd.killcaffeine.history.today.HistoryTodayRegisterActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryRegisterChoiceFranchiseFragment : BaseFragment<FragmentHistoryRegisterChoiceFranchiseBinding, HistoryRegisterChoiceFranchiseViewModel>() {

    companion object {
        fun newInstance() = HistoryRegisterChoiceFranchiseFragment()
    }

    override val mViewModel: HistoryRegisterChoiceFranchiseViewModel by viewModel()
    override val resourceId: Int
        get() = R.layout.fragment_history_register_choice_franchise

    override fun initViewStart() {

    }

    override fun initDataBinding() {

    }

    override fun initViewFinal() {
        getFragmentBinding().fragmentHistoryChoiceFranchise1.run {
            load(R.drawable.franchise_sample){ transformations(CircleCropTransformation()) }
            setOnClickListener {
                (activity as? HistoryTodayRegisterActivity)?.replaceMenuFragment()
            }
        }

        getFragmentBinding().fragmentHistoryChoiceFranchise2.load(R.drawable.franchise_sample){
            transformations(CircleCropTransformation())
        }

        getFragmentBinding().fragmentHistoryChoiceFranchise3.load(R.drawable.franchise_sample){
            transformations(CircleCropTransformation())
        }

        getFragmentBinding().fragmentHistoryChoiceFranchise4.load(R.drawable.franchise_sample){
            transformations(CircleCropTransformation())
        }

        getFragmentBinding().fragmentHistoryChoiceFranchise5.load(R.drawable.franchise_sample){
            transformations(CircleCropTransformation())
        }

        getFragmentBinding().fragmentHistoryChoiceFranchise6.load(R.drawable.franchise_sample){
            transformations(CircleCropTransformation())
        }

        getFragmentBinding().fragmentHistoryChoiceFranchise7.load(R.drawable.franchise_sample){
            transformations(CircleCropTransformation())
        }

        getFragmentBinding().fragmentHistoryChoiceFranchise8.load(R.drawable.franchise_sample){
            transformations(CircleCropTransformation())
        }

        getFragmentBinding().fragmentHistoryChoiceFranchise9.load(R.drawable.franchise_sample){
            transformations(CircleCropTransformation())
        }

        getFragmentBinding().fragmentHistoryChoiceFranchise10.load(R.drawable.franchise_sample){
            transformations(CircleCropTransformation())
        }

        getFragmentBinding().fragmentHistoryChoiceFranchise11.load(R.drawable.franchise_sample){
            transformations(CircleCropTransformation())
        }

        getFragmentBinding().fragmentHistoryChoiceFranchise12.load(R.drawable.franchise_sample){
            transformations(CircleCropTransformation())
        }

        getFragmentBinding().fragmentHistoryChoiceFranchise13.load(R.drawable.franchise_sample){
            transformations(CircleCropTransformation())
        }

        getFragmentBinding().fragmentHistoryChoiceFranchise14.load(R.drawable.franchise_sample){
            transformations(CircleCropTransformation())
        }

        getFragmentBinding().fragmentHistoryChoiceFranchise15.load(R.drawable.franchise_sample){
            transformations(CircleCropTransformation())
        }

        getFragmentBinding().fragmentHistoryChoiceFranchise16.load(R.drawable.franchise_sample){
            transformations(CircleCropTransformation())
        }
    }
}