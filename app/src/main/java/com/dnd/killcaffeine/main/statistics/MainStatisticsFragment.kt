/*
 * Created by Lee Oh Hyoung on 2019-07-30 .. 
 */
package com.dnd.killcaffeine.main.statistics

import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.base.BaseFragment
import com.dnd.killcaffeine.databinding.FragmentStatisticsBinding
import kotlinx.android.synthetic.main.fragment_statistics.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainStatisticsFragment : BaseFragment<FragmentStatisticsBinding, MainStatisticsViewModel>() {

    override val mViewModel: MainStatisticsViewModel by viewModel()
    override val resourceId: Int
        get() = R.layout.fragment_statistics

    override fun initViewStart() {
    }

    override fun initDataBinding() {
    }

    override fun initViewFinal() {

        /*activity?.run {
            fragment_statistics_graph_daily.run {
                elevation = 6.0f
                load(R.drawable.image_statistic_graph_daily) {
                    transformations(RoundedCornersTransformation(10f))
                }
            }
        }*/
    }
}