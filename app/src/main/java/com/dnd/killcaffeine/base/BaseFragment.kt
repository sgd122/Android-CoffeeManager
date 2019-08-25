/*
 * Created by Lee Oh Hyoung on 2019-07-30 .. 
 */
package com.dnd.killcaffeine.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<T : ViewDataBinding, V: BaseViewModel> : Fragment(), BaseView {

    abstract val resourceId: Int

    abstract val mViewModel: V
    private lateinit var mBinding: T

    abstract fun initViewStart()

    abstract fun initDataBinding()

    abstract fun initViewFinal()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        mBinding = DataBindingUtil.inflate(inflater, resourceId, container, false)
        mBinding.lifecycleOwner = this

        snackbarObserving()
        initViewStart()
        initDataBinding()
        initViewFinal()

        return mBinding.root
    }

    fun getFragmentBinding() = mBinding

    override fun setToolbar(resourceId: Int?, title: String?) {
        resourceId?.let { id ->
            activity?.findViewById<Toolbar>(id)?.let { toolbar ->
                title?.let { value -> toolbar.title = value }
            }

            activity?.actionBar?.also { actionBar ->
                actionBar.setDisplayHomeAsUpEnabled(true)
                actionBar.setDisplayHomeAsUpEnabled(true)
            } ?: activity?.setActionBar(null); return@let
        }
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
            activity?.findViewById<View>(android.R.id.content)?.let { view ->
                val snackbar: Snackbar = Snackbar.make(view, string, Snackbar.LENGTH_SHORT)
                snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)?.maxLines = 5
                snackbar.show()
            }
        })

        mViewModel.snackbarResIdLiveData.observe(this, Observer { resId ->
            activity?.findViewById<View>(android.R.id.content)?.let { view ->
                val snackbar: Snackbar = Snackbar.make(view, resId, Snackbar.LENGTH_SHORT)
                snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)?.maxLines = 5
                snackbar.show()
            }
        })
    }

    internal fun showSnackbar(message: String, duration: Int = Snackbar.LENGTH_SHORT){
        activity?.findViewById<View>(android.R.id.content)?.let { view ->

            val snackbar: Snackbar = if(duration != Snackbar.LENGTH_SHORT || duration != Snackbar.LENGTH_LONG || duration != Snackbar.LENGTH_INDEFINITE){
                Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
            } else {
                Snackbar.make(view, message, duration)
            }

            snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)?.maxLines = 5
            snackbar.show()
        }
    }

    internal fun showSnackbar(stringRes: Int, duration: Int = Snackbar.LENGTH_SHORT){
        activity?.findViewById<View>(android.R.id.content)?.let { view ->

            val snackbar: Snackbar = if(duration != Snackbar.LENGTH_SHORT || duration != Snackbar.LENGTH_LONG || duration != Snackbar.LENGTH_INDEFINITE){
                Snackbar.make(view, stringRes, Snackbar.LENGTH_SHORT)
            } else {
                Snackbar.make(view, stringRes, duration)
            }

            snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)?.maxLines = 5
            snackbar.show()
        }
    }
}