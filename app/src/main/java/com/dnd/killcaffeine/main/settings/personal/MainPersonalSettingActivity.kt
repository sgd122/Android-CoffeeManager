/*
 * Created by Lee Oh Hyoung on 2019-08-31..
 */
package com.dnd.killcaffeine.main.settings.personal

import android.content.Context
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.forEach
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.base.BaseActivity
import com.dnd.killcaffeine.databinding.ActivitySettingsPersonalBinding
import kotlinx.android.synthetic.main.activity_settings_personal.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainPersonalSettingActivity : BaseActivity<ActivitySettingsPersonalBinding, MainPersonalSettingViewModel>(){

    override val mViewModel: MainPersonalSettingViewModel by viewModel()
    override val resourceId: Int
        get() = R.layout.activity_settings_personal

    private var personalWeight: Int = 0

    override fun initViewStart() {
        window?.decorView?.let { view ->
            setupKeyboardHide(view, this)
        }
    }

    override fun initDataBinding() {
    }

    override fun initViewFinal() {

        activity_settings_personal_weight_edit_text.let { editText ->

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

            editText.setOnFocusChangeListener { _, focused ->
                when(focused) {
                    true -> {
                        editText.setText("")
                        editText.filters= arrayOf(InputFilter.LengthFilter(3))
                    }
                    false -> {
                        personalWeight = when(editText.text.toString() == ""){
                            true -> 0
                            false -> editText.text.toString().toInt()
                        }
                        editText.filters = arrayOf(InputFilter.LengthFilter(5))
                        editText.setText(getString(R.string.main_settings_personal_weight_form, personalWeight.toString()))

                        Toast.makeText(applicationContext, "weight : $personalWeight", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        activity_settings_personal_back_button.setOnClickListener {
            finish()
        }

        activity_settings_personal_save_button.setOnClickListener {
            finish()
        }
    }

    private fun editTextClearFocus(view: View, editText: EditText){
        editText.clearFocus()
        val imm: InputMethodManager = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}