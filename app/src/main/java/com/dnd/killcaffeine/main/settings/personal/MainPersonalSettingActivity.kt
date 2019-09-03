/*
 * Created by Lee Oh Hyoung on 2019-08-31..
 */
package com.dnd.killcaffeine.main.settings.personal

import android.content.Context
import android.text.InputFilter
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.view.forEach
import androidx.lifecycle.Observer
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.base.BaseActivity
import com.dnd.killcaffeine.databinding.ActivitySettingsPersonalBinding
import com.dnd.killcaffeine.model.data.room.personal.Personal
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_settings_personal.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainPersonalSettingActivity : BaseActivity<ActivitySettingsPersonalBinding, MainPersonalSettingViewModel>(){

    override val mViewModel: MainPersonalSettingViewModel by viewModel()
    override val resourceId: Int
        get() = R.layout.activity_settings_personal

    override fun initViewStart() {
        window?.decorView?.let { view ->
            setupKeyboardHide(view, this)
        }
    }

    override fun initDataBinding() {
        mViewModel.insertPersonalLiveData.observe(this, Observer {
            finish()
        })
    }

    override fun initViewFinal() {

        activity_settings_personal_radio_group.setOnCheckedChangeListener { _, _ ->
            clearWeightEditText()
            clearRecommendEditText()
        }

        activity_settings_personal_weight_edit_text.let { editText ->

            setupViewFocusOut(editText)
            setupEditTextTemplate(editText)
        }

        // 뒤로가기
        activity_settings_personal_back_button.setOnClickListener {
            finish()
        }

        // 저장하기
        activity_settings_personal_save_button.setOnClickListener {
            val checkedButtonId: Int = activity_settings_personal_radio_group.checkedRadioButtonId
            val weight: Int = mViewModel.getPersonalWeight()


            if(mViewModel.checkPersonalInfo(checkedButtonId, weight)){
                mViewModel.createPersonalInfo(
                    radioButtonId = checkedButtonId,
                    weight = weight
                )
            }
        }
    }

    private fun editTextClearFocus(view: View, editText: EditText){
        editText.clearFocus()
        val imm: InputMethodManager = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun setupViewFocusOut(editText: EditText){
        activity_settings_personal_radio_group.forEach {
            it.setOnClickListener { view ->
                if(editText.isFocused) {
                    editTextClearFocus(view, editText)
                }
            }
        }

        activity_settings_personal_linear_layout.setOnTouchListener { view, _ ->
            if(editText.isFocused){
                editTextClearFocus(view, editText)
            }
            return@setOnTouchListener false
        }
    }

    private fun setupEditTextTemplate(editText: EditText){
        editText.setOnFocusChangeListener { _, focused ->
            when(focused) {
                true -> {
                    editText.setText("")
                    clearRecommendEditText()
                    editText.filters= arrayOf(InputFilter.LengthFilter(3))
                }
                false -> {
                    mViewModel.setPersonalWeight(when(editText.text.toString() == ""){
                        true -> 0
                        false -> editText.text.toString().toInt()
                    })
                    editText.filters = arrayOf(InputFilter.LengthFilter(5))
                    editText.setText(getString(R.string.main_settings_personal_weight_form, mViewModel.getPersonalWeight().toString()))
                    setupRecommendEditText(mViewModel.getPersonalWeight())
                }
            }
        }
    }

    private fun clearWeightEditText(){
        activity_settings_personal_weight_edit_text.setText("")
    }

    private fun clearRecommendEditText(){
        activity_settings_personal_recommend_caffeine_text_view.text = ""
    }

    private fun setupRecommendEditText(weight: Int){
        val recommend:Int = mViewModel.recommendIntakeCalculation(activity_settings_personal_radio_group.checkedRadioButtonId, weight)
        activity_settings_personal_recommend_caffeine_text_view.text = getString(R.string.main_settings_personal_recommend_form, recommend.toString())
    }
}