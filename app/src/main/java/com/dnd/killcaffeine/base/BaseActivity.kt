/*
 * Created by Lee Oh Hyoung on 2019-07-30 .. 
 */
package com.dnd.killcaffeine.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.dnd.killcaffeine.R
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity<T : ViewDataBinding, V: BaseViewModel> : AppCompatActivity(), BaseView {

    abstract val resourceId: Int

    abstract val mViewModel: V
    private lateinit var mBinding: T

    abstract fun initViewStart()

    abstract fun initDataBinding()

    abstract fun initViewFinal()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, resourceId)
        mBinding.lifecycleOwner = this

        snackbarObserving()
        initViewStart()
        initDataBinding()
        initViewFinal()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun setToolbar(resourceId: Int?, title: String?) {
        resourceId?.let {
            setSupportActionBar(findViewById<Toolbar>(it))
            supportActionBar?.run {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowTitleEnabled(true)
                title?.let { value -> setTitle(value) } ?: setTitle("커피 매니저")

            }
        } ?: setSupportActionBar(null)
    }

    override fun setupKeyboardHide(view: View, activity: Activity?) {
        if(view !is EditText || view !is Button){
            view.setOnTouchListener { _, _ ->
                activity?.let {
                    val imm = it.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(it.currentFocus?.windowToken, 0)
                }
                return@setOnTouchListener false
            }
        }
        if(view is ViewGroup){
            for(i in 0 until view.childCount){
                setupKeyboardHide(view.getChildAt(i), activity)
            }
        }
    }

    private fun snackbarObserving(){
        mViewModel.snackbarLiveData.observe(this, Observer { string ->
            findViewById<View>(android.R.id.content)?.let { view ->
                val snackbar: Snackbar= Snackbar.make(view, string, Snackbar.LENGTH_SHORT)
                snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)?.maxLines = 5
                snackbar.show()
            }
        })

        mViewModel.snackbarResIdLiveData.observe(this, Observer { resId ->
            findViewById<View>(android.R.id.content)?.let { view ->
                val snackbar: Snackbar = Snackbar.make(view, resId, Snackbar.LENGTH_SHORT)
                snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)?.maxLines = 5
                snackbar.show()
            }
        })
    }
}