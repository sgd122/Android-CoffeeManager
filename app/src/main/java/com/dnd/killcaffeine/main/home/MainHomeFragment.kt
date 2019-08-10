/*
 * Created by Lee Oh Hyoung on 2019-07-30 .. 
 */
package com.dnd.killcaffeine.main.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.base.BaseFragment
import com.dnd.killcaffeine.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainHomeFragment : BaseFragment<FragmentHomeBinding, MainHomeViewModel>() {

    companion object {
        fun newInstance() = MainHomeFragment()
    }

    private val TAG = javaClass.name

    override val mViewModel: MainHomeViewModel by viewModel()
    override val resourceId: Int
        get() = R.layout.fragment_home

    override fun initViewStart() {

    }

    override fun initDataBinding() {
        mViewModel.decaffeineMenuLiveData.observe(this, Observer { result ->
            result?.let {
                Log.d(TAG, "디카페인 메뉴 사이즈 : ${result.list.size}")
                it.list.forEachIndexed { index, menu ->
                    Log.d(TAG, "$index 번째 메뉴")
                    Log.d(TAG, "$menu")
                }
            } ?: Log.d(TAG, "디카페인 API call 실패")
        })

        mViewModel.franchiseMenuLiveData.observe(this, Observer { result ->
            result?.let {
                Log.d(TAG, "프랜차이즈 메뉴 사이즈 : ${result.list.size}")
                it.list.forEachIndexed { index, franchise ->
                    Log.d(TAG, "$index 번째 프랜차이즈 : ${franchise.franchiseName}")
                    Log.d(TAG, "[메뉴]")
                    franchise.menu.forEach { menu ->
                        Log.d(TAG, "$menu")
                    }
                }
            } ?: Log.d(TAG, "프랜차이즈 API call 실패")
        })
    }

    override fun initViewFinal() {
        //mViewModel.getDecaffeineMenuList()

        mViewModel.getFranchiseMenuList()
    }
}