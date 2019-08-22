/*
 * Created by Lee Oh Hyoung on 2019-08-22 .. 
 */
package com.dnd.killcaffeine.main.home.show_more

import androidx.recyclerview.widget.GridLayoutManager
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.RequestCode
import com.dnd.killcaffeine.base.BaseActivity
import com.dnd.killcaffeine.databinding.ActivityTodayRecommendDrinkBinding
import com.dnd.killcaffeine.main.home.recyclerview.DecaffeineRecyclerViewAdpater
import com.dnd.killcaffeine.model.data.menu.Menu
import com.dnd.killcaffeine.utils.RecyclerViewItemMargin
import kotlinx.android.synthetic.main.activity_today_recommend_drink.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.ClassCastException

class TodayRecommendDrinkActivity : BaseActivity<ActivityTodayRecommendDrinkBinding, TodayRecommendDrinkViewModel>() {

    companion object {
        private const val RECYCLER_VIEW_SPAN_COUNT = 2
    }

    private val mTAG = javaClass.simpleName

    override val resourceId: Int
        get() = R.layout.activity_today_recommend_drink

    override val mViewModel: TodayRecommendDrinkViewModel by viewModel()
    private val mDecaffeineRecyclerViewAdapter: DecaffeineRecyclerViewAdpater by inject()

    override fun initViewStart() {

        // Home Fragment 에서 가지고 온 Menu 리스트
        when(intent.hasExtra(RequestCode.DECAFFEINE_TODAY_RECOMMEND_SHOW_MORE)) {
            true -> {
                try {
                    mDecaffeineRecyclerViewAdapter.setDecaffeineArrayList(intent.getSerializableExtra(RequestCode.DECAFFEINE_TODAY_RECOMMEND_SHOW_MORE) as ArrayList<Menu>)
                } catch (e: ClassCastException){
                    e.printStackTrace()
                    mDecaffeineRecyclerViewAdapter.setDecaffeineArrayList(ArrayList())
                }
            }
            false -> {
                mDecaffeineRecyclerViewAdapter.setDecaffeineArrayList(ArrayList())
            }
        }

        activity_today_recommend_drink_recycler_view.apply {
            layoutManager = GridLayoutManager(this@TodayRecommendDrinkActivity, RECYCLER_VIEW_SPAN_COUNT)
            adapter = mDecaffeineRecyclerViewAdapter
            addItemDecoration(RecyclerViewItemMargin(32, 0))
            //addItemDecoration(GridLayoutEqualColumnDecorationSpacing(this@TodayRecommendDrinkActivity, R.dimen.activity_today_recommend_column_spacing_half))
        }
    }

    override fun initDataBinding() {
    }

    override fun initViewFinal() {
        activity_today_recommend_drink_back_button.setOnClickListener {
            finish()
        }
    }
}