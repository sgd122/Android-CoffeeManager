/*
 * Created by Lee Oh Hyoung on 2019-07-30 .. 
 */
package com.dnd.killcaffeine.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dnd.killcaffeine.R
import kotlinx.android.synthetic.main.home_fragment.*

class MainHomeFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

    return inflater.inflate(R.layout.home_fragment, container, false)

}

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    tv_home_fragment.text = "Fragment Home"
}
}