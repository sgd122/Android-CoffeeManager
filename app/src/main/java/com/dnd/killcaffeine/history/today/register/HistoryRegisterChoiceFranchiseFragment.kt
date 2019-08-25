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
        getFragmentBinding().fragmentHistoryChoiceFranchise1.run {
            load(R.drawable.image_logo_starbucks){ transformations(CircleCropTransformation()) }
            setOnClickListener {
                (activity as? HistoryTodayRegisterActivity)?.replaceMenuFragment()
            }
        }

        // 이디야
        getFragmentBinding().fragmentHistoryChoiceFranchise2.run{
            load(R.drawable.image_logo_ediya){
                scaleType = ImageView.ScaleType.CENTER_CROP
                transformations(CircleCropTransformation())
            }
        }

        // 투썸
        getFragmentBinding().fragmentHistoryChoiceFranchise3.run{
            load(R.drawable.image_logo_twosome){
                transformations(CircleCropTransformation())
            }
        }

        // 파스쿠치
        getFragmentBinding().fragmentHistoryChoiceFranchise4.run{
            load(R.drawable.image_logo_pascucci){
                transformations(CircleCropTransformation())
            }
        }

        // 할리스
        getFragmentBinding().fragmentHistoryChoiceFranchise5.run{
            load(R.drawable.image_logo_hollys){
                transformations(CircleCropTransformation())
            }
        }

        // 달콤
        getFragmentBinding().fragmentHistoryChoiceFranchise6.run{
            load(R.drawable.image_logo_dalkomm){
                transformations(CircleCropTransformation())
            }
        }

        // 커피스미스
        getFragmentBinding().fragmentHistoryChoiceFranchise7.run{
            load(R.drawable.image_logo_smith){
                transformations(CircleCropTransformation())
            }
        }

        // 엔젤리너스
        getFragmentBinding().fragmentHistoryChoiceFranchise8.run{
            load(R.drawable.image_logo_angel){
                transformations(CircleCropTransformation())
            }
        }

        // 탐앤탐스
        getFragmentBinding().fragmentHistoryChoiceFranchise9.run{
            load(R.drawable.image_logo_tomntoms){
                transformations(CircleCropTransformation())
            }
        }

        // 폴바셋
        getFragmentBinding().fragmentHistoryChoiceFranchise10.run{
            load(R.drawable.image_logo_paulbassett){
                transformations(CircleCropTransformation())
            }
        }

        // 커피빈
        getFragmentBinding().fragmentHistoryChoiceFranchise11.run{
            load(R.drawable.image_logo_coffeebean){
                transformations(CircleCropTransformation())
            }
        }

        // 요거프레소
        getFragmentBinding().fragmentHistoryChoiceFranchise12.run{
            load(R.drawable.image_logo_yogerpresso){
                transformations(CircleCropTransformation())
            }
        }

        // 빽다방
        getFragmentBinding().fragmentHistoryChoiceFranchise13.run{
            load(R.drawable.image_logo_bback){
                transformations(CircleCropTransformation())
            }
        }

        // 드롭탑
        getFragmentBinding().fragmentHistoryChoiceFranchise14.run{
            load(R.drawable.image_logo_droptop){
                transformations(CircleCropTransformation())
            }
        }

        // 아티제
        getFragmentBinding().fragmentHistoryChoiceFranchise15.run{
            load(R.drawable.image_logo_artisee){
                transformations(CircleCropTransformation())
            }
        }

        // 셀렉토커피
        getFragmentBinding().fragmentHistoryChoiceFranchise16.run{
            load(R.drawable.image_logo_selecto){
                transformations(CircleCropTransformation())
            }
        }

        // 메머드커피(Mmth)
        getFragmentBinding().fragmentHistoryChoiceFranchise17.run{
            load(R.drawable.image_logo_mmth){
                transformations(CircleCropTransformation())
            }
        }

        // 공차
        getFragmentBinding().fragmentHistoryChoiceFranchise18.run{
            load(R.drawable.image_logo_gongcha){
                transformations(CircleCropTransformation())
            }
        }

        // 오설록
        getFragmentBinding().fragmentHistoryChoiceFranchise19.run{
            load(R.drawable.image_logo_osulloc){
                transformations(CircleCropTransformation())
            }
        }

        // 배스킨라빈스
        getFragmentBinding().fragmentHistoryChoiceFranchise20.run{
            load(R.drawable.image_logo_baskin){
                transformations(CircleCropTransformation())
            }
        }
    }
}