package com.dnd.killcaffeine.detail

import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.base.BaseActivity
import com.dnd.killcaffeine.databinding.ActivityDetailBinding
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/*
 * Created by iohyeong on 2019-08-15..
 */
class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {

    override val mViewModel: DetailViewModel by viewModel()
    override val resourceId: Int
        get() = R.layout.activity_detail

    override fun initViewStart() {

        setToolbar(R.id.activity_detail_toolbar)

        intent?.run {
            if(hasExtra("menu")){
                activity_detail_text_view.text = getSerializableExtra("menu")?.toString() ?: "인텐트를 넘겨받지 못했습니다"
            }
        }


    }

    override fun initDataBinding() {
    }

    override fun initViewFinal() {
    }
}