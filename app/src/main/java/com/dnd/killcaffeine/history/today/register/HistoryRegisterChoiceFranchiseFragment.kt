/*
 * Created by Lee Oh Hyoung on 2019-08-25..
 */
package com.dnd.killcaffeine.history.today.register

import android.widget.ImageView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.base.BaseFragment
import com.dnd.killcaffeine.databinding.FragmentHistoryRegisterChoiceFranchiseBinding
import com.dnd.killcaffeine.history.today.HistoryTodayRegisterActivity
import kotlinx.android.synthetic.main.fragment_history_register_choice_franchise.*
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

        // 스타벅스
        fragment_history_choice_franchise_1.run {
            load(R.drawable.image_logo_starbucks){ transformations(CircleCropTransformation()) }
            setOnClickListener {
                (activity as? HistoryTodayRegisterActivity)?.replaceMenuFragment()
            }
        }

        // 이디야
        fragment_history_choice_franchise_2.run{
            load(R.drawable.image_logo_ediya){
                scaleType = ImageView.ScaleType.CENTER_CROP
                transformations(CircleCropTransformation())
            }
        }

        // 투썸
        fragment_history_choice_franchise_3.run{
            load(R.drawable.image_logo_twosome){
                transformations(CircleCropTransformation())
            }
        }

        // 파스쿠치
        fragment_history_choice_franchise_4.run{
            load(R.drawable.image_logo_pascucci){
                transformations(CircleCropTransformation())
            }
        }

        // 할리스
        fragment_history_choice_franchise_5.run{
            load(R.drawable.image_logo_hollys){
                transformations(CircleCropTransformation())
            }
        }

        // 달콤
        fragment_history_choice_franchise_6.run{
            load(R.drawable.image_logo_dalkomm){
                transformations(CircleCropTransformation())
            }
        }

        // 커피스미스
        fragment_history_choice_franchise_7.run{
            load(R.drawable.image_logo_smith){
                transformations(CircleCropTransformation())
            }
        }

        // 엔젤리너스
        fragment_history_choice_franchise_8.run{
            load(R.drawable.image_logo_angel){
                transformations(CircleCropTransformation())
            }
        }

        // 탐앤탐스
        fragment_history_choice_franchise_9.run{
            load(R.drawable.image_logo_tomntoms){
                transformations(CircleCropTransformation())
            }
        }

        // 폴바셋
        fragment_history_choice_franchise_10.run{
            load(R.drawable.image_logo_paulbassett){
                transformations(CircleCropTransformation())
            }
        }

        // 커피빈
        fragment_history_choice_franchise_11.run{
            load(R.drawable.image_logo_coffeebean){
                transformations(CircleCropTransformation())
            }
        }

        // 요거프레소
        fragment_history_choice_franchise_12.run{
            load(R.drawable.image_logo_yogerpresso){
                transformations(CircleCropTransformation())
            }
        }

        // 빽다방
        fragment_history_choice_franchise_13.run{
            load(R.drawable.image_logo_bback){
                transformations(CircleCropTransformation())
            }
        }

        // 드롭탑
        fragment_history_choice_franchise_14.run{
            load(R.drawable.image_logo_droptop){
                transformations(CircleCropTransformation())
            }
        }

        // 아티제
        fragment_history_choice_franchise_15.run{
            load(R.drawable.image_logo_artisee){
                transformations(CircleCropTransformation())
            }
        }

        // 셀렉토커피
        fragment_history_choice_franchise_16.run{
            load(R.drawable.image_logo_selecto){
                transformations(CircleCropTransformation())
            }
        }

        // 메머드커피(Mmth)
        fragment_history_choice_franchise_17.run{
            load(R.drawable.image_logo_mmth){
                transformations(CircleCropTransformation())
            }
        }

        // 공차
        fragment_history_choice_franchise_18.run{
            load(R.drawable.image_logo_gongcha){
                transformations(CircleCropTransformation())
            }
        }

        // 오설록
        fragment_history_choice_franchise_19.run{
            load(R.drawable.image_logo_osulloc){
                transformations(CircleCropTransformation())
            }
        }

        // 배스킨라빈스
        fragment_history_choice_franchise_20.run{
            load(R.drawable.image_logo_baskin){
                transformations(CircleCropTransformation())
            }
        }
    }
}