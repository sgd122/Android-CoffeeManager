/*
 * Created by Lee Oh Hyoung on 2019-07-30 .. 
 */
package com.dnd.killcaffeine.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.dialog.LoadingIndicator
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity<T : ViewDataBinding, V: BaseViewModel> : AppCompatActivity(), BaseView {

    abstract val resourceId: Int

    abstract val mViewModel: V
    private lateinit var mBinding: T

    private var mLoadingIndicator: LoadingIndicator? = null

    abstract fun initViewStart()

    abstract fun initDataBinding()

    abstract fun initViewFinal()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, resourceId)
        mBinding.lifecycleOwner = this

        mLoadingIndicator = LoadingIndicator(this)

        loadingIndicatorObserving()
        snackbarObserving()
        initViewStart()
        initDataBinding()
        initViewFinal()
    }

    override fun onStop() {
        super.onStop()
        mLoadingIndicator?.dismiss()
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
            setSupportActionBar(findViewById(it))
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

    override fun loadingIndicatorObserving() {
        mViewModel.loadingIndicatorLiveData.observe(this, Observer { control ->
            when(control) {
                true -> {
                    if(!isFinishing) {
                        mLoadingIndicator?.show()
                    }
                }
                false -> mLoadingIndicator?.cancel()
            }
        })
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    override fun startActivityForResult(intent: Intent?, requestCode: Int) {
        super.startActivityForResult(intent, requestCode)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    override fun showNotImplementToast(applicationContext: Context) { // should be application context
        val layout: View = layoutInflater.inflate(
            R.layout.toast_not_implement,
            findViewById(R.id.dialog_not_implement_parent_linear_layout)
        )

        val toast = Toast(applicationContext).apply {
            setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 32)
            duration = Toast.LENGTH_SHORT
            view = layout
        }

        toast.show()
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

    internal fun showSnackbar(message: String, duration: Int = Snackbar.LENGTH_SHORT){
        findViewById<View>(android.R.id.content)?.let { view ->

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
        findViewById<View>(android.R.id.content)?.let { view ->

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