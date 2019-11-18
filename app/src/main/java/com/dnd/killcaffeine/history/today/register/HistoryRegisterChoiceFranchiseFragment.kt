/*
 * Created by Lee Oh Hyoung on 2019-08-25..
 */
package com.dnd.killcaffeine.history.today.register

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.RequestCode
import com.dnd.killcaffeine.base.BaseFragment
import com.dnd.killcaffeine.databinding.FragmentHistoryRegisterChoiceFranchiseBinding
import com.dnd.killcaffeine.dialog.HistoryRegisterWarningDialog
import com.dnd.killcaffeine.history.today.HistoryTodayRegisterActivity
import com.dnd.killcaffeine.model.data.room.menu.Menu
import com.dnd.killcaffeine.recyclerview.RecentDrinkAdapter
import kotlinx.android.synthetic.main.fragment_history_register_choice_franchise.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryRegisterChoiceFranchiseFragment : BaseFragment<FragmentHistoryRegisterChoiceFranchiseBinding, HistoryRegisterChoiceFranchiseViewModel>() {

    companion object {
        fun newInstance() = HistoryRegisterChoiceFranchiseFragment()
    }

    override val mViewModel: HistoryRegisterChoiceFranchiseViewModel by viewModel()
    override val resourceId: Int
        get() = R.layout.fragment_history_register_choice_franchise

    private val mCustomCoffeeAdapter: RecentDrinkAdapter by inject()

    override fun initViewStart() {
        setupKeyboardHide(
            view = fragment_history_register_choice_franchise_parent_linear_layout,
            activity = activity
        )

        mCustomCoffeeAdapter.apply {
            setRecentDrinkArrayList(insertCustomMenu())
            setOnRecentDrinkItemClickListener(object: RecentDrinkAdapter.OnRecentDrinkItemClickListener {
                override fun onItemClick(menu: Menu) {
                    showWarningDialog(menu)
                }
            })
        }

        fragment_history_register_franchise_not_found_recycler_view.run {
            layoutManager = LinearLayoutManager(activity?.applicationContext, RecyclerView.HORIZONTAL, false)
            adapter = mCustomCoffeeAdapter
        }

    }

    override fun initDataBinding() {

    }

    override fun initViewFinal() {

        // 스타벅스
        fragment_history_choice_franchise_1.run {
            load(R.drawable.image_logo_starbucks){ transformations(CircleCropTransformation()) }
            setOnClickListener {
                (activity as? HistoryTodayRegisterActivity)?.run {
                    HistoryTodayRegisterActivity.chosenFranchiseMenu = MenuCollection.starBucks()
                    replaceMenuFragment()
                }
            }
        }

        // 이디야
        fragment_history_choice_franchise_2.run{
            load(R.drawable.image_logo_ediya){ transformations(CircleCropTransformation()) }
            setOnClickListener {
                (activity as? HistoryTodayRegisterActivity)?.run{
                    HistoryTodayRegisterActivity.chosenFranchiseMenu = MenuCollection.ediya()
                    replaceMenuFragment()
                }
            }
        }

        // 할리스
        fragment_history_choice_franchise_3.run{
            load(R.drawable.image_logo_hollys){ transformations(CircleCropTransformation()) }
            setOnClickListener {
                (activity as? HistoryTodayRegisterActivity)?.run{
                    HistoryTodayRegisterActivity.chosenFranchiseMenu = MenuCollection.hollys()
                    replaceMenuFragment()
                }
            }
        }

        // 엔젤리너스
        fragment_history_choice_franchise_4.run{
            load(R.drawable.image_logo_angel){ transformations(CircleCropTransformation()) }
            setOnClickListener {
                (activity as? HistoryTodayRegisterActivity)?.run{
                    HistoryTodayRegisterActivity.chosenFranchiseMenu = MenuCollection.angelinus()
                    replaceMenuFragment()
                }
            }
        }

        // 투썸
        fragment_history_choice_franchise_5.run{
            load(R.drawable.image_logo_twosome){ transformations(CircleCropTransformation()) }
            setOnClickListener {
                activity?.applicationContext?.let {
                    showNotImplementToast(it)
                }
            }

        }

        // 달콤
        fragment_history_choice_franchise_6.run{
            load(R.drawable.image_logo_dalkomm){ transformations(CircleCropTransformation()) }
            setOnClickListener {
                activity?.applicationContext?.let {
                    showNotImplementToast(it)
                }
            }
        }

        // 커피스미스
        fragment_history_choice_franchise_7.run{
            load(R.drawable.image_logo_smith){ transformations(CircleCropTransformation()) }
            setOnClickListener {
                activity?.applicationContext?.let {
                    showNotImplementToast(it)
                }
            }
        }

        // 파스쿠치
        fragment_history_choice_franchise_8.run{
            load(R.drawable.image_logo_pascucci){ transformations(CircleCropTransformation()) }
            setOnClickListener {
                activity?.applicationContext?.let {
                    showNotImplementToast(it)
                }
            }
        }

        // 탐앤탐스
        fragment_history_choice_franchise_9.run{
            load(R.drawable.image_logo_tomntoms){ transformations(CircleCropTransformation()) }
            setOnClickListener {
                activity?.applicationContext?.let {
                    showNotImplementToast(it)
                }
            }
        }

        // 폴바셋
        fragment_history_choice_franchise_10.run{
            load(R.drawable.image_logo_paulbassett){ transformations(CircleCropTransformation()) }
            setOnClickListener {
                activity?.applicationContext?.let {
                    showNotImplementToast(it)
                }
            }
        }

        // 커피빈
        fragment_history_choice_franchise_11.run{
            load(R.drawable.image_logo_coffeebean){ transformations(CircleCropTransformation()) }
            setOnClickListener {
                activity?.applicationContext?.let {
                    showNotImplementToast(it)
                }
            }
        }

        // 요거프레소
        fragment_history_choice_franchise_12.run{
            load(R.drawable.image_logo_yogerpresso){ transformations(CircleCropTransformation()) }
            setOnClickListener {
                activity?.applicationContext?.let {
                    showNotImplementToast(it)
                }
            }
        }

        // 빽다방
        fragment_history_choice_franchise_13.run{
            load(R.drawable.image_logo_bback){ transformations(CircleCropTransformation()) }
            setOnClickListener {
                activity?.applicationContext?.let {
                    showNotImplementToast(it)
                }
            }
        }

        // 드롭탑
        fragment_history_choice_franchise_14.run{
            load(R.drawable.image_logo_droptop){ transformations(CircleCropTransformation()) }
            setOnClickListener {
                activity?.applicationContext?.let {
                    showNotImplementToast(it)
                }
            }
        }

        // 아티제
        fragment_history_choice_franchise_15.run{
            load(R.drawable.image_logo_artisee){ transformations(CircleCropTransformation()) }
            setOnClickListener {
                activity?.applicationContext?.let {
                    showNotImplementToast(it)
                }
            }
        }

        // 셀렉토커피
        fragment_history_choice_franchise_16.run{
            load(R.drawable.image_logo_selecto){ transformations(CircleCropTransformation()) }
            setOnClickListener {
                activity?.applicationContext?.let {
                    showNotImplementToast(it)
                }
            }
        }

        // 메머드커피(Mmth)
        fragment_history_choice_franchise_17.run{
            load(R.drawable.image_logo_mmth){ transformations(CircleCropTransformation()) }
                setOnClickListener {
                    activity?.applicationContext?.let {
                        showNotImplementToast(it)
                    }
                }
        }

        // 공차
        fragment_history_choice_franchise_18.run{
            load(R.drawable.image_logo_gongcha){ transformations(CircleCropTransformation()) }
            setOnClickListener {
                activity?.applicationContext?.let {
                    showNotImplementToast(it)
                }
            }
        }

        // 오설록
        fragment_history_choice_franchise_19.run{
            load(R.drawable.image_logo_osulloc){ transformations(CircleCropTransformation()) }
            setOnClickListener {
                activity?.applicationContext?.let {
                    showNotImplementToast(it)
                }
            }
        }

        // 배스킨라빈스
        fragment_history_choice_franchise_20.run{
            load(R.drawable.image_logo_baskin){ transformations(CircleCropTransformation()) }
            setOnClickListener {
                activity?.applicationContext?.let {
                    showNotImplementToast(it)
                }
            }
        }
    }

    private fun insertCustomMenu(): ArrayList<Menu> {
        return arrayListOf(
            Menu( "샷 한번 커피", "카페", 90, R.drawable.image_decaffeine, true),
            Menu( "샷 두번 커피", "카페", 180,R.drawable.image_decaffeine, true),
            Menu( "샷 세번 커피", "카페", 270,R.drawable.image_decaffeine,true),
            Menu( "디카페인 커피", "카페", 5,  R.drawable.image_decaffeine,true),
            Menu( "따뜻한 차", "차", 0,R.drawable.image_tea, true),
            Menu( "차가운 차", "차", 0, R.drawable.image_tea,true)
        )
    }

    private fun showWarningDialog(menu: Menu){
        activity?.let { activity ->
            HistoryRegisterWarningDialog(activity, View.OnClickListener {
                activity.setResult(Activity.RESULT_OK, Intent().apply {
                    putExtra(RequestCode.HISTORY_REGISTER_SUCCESS_MENU, menu)
                })
                activity.finish()
            }).show()
        }
    }
}