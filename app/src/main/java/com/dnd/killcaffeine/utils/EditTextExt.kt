/*
 * Created by Lee Oh Hyoung on 2019-09-16..
 */
package com.dnd.killcaffeine.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.afterTextChanged(afterTextChanged: (String) -> (Unit)) {
    this.addTextChangedListener(object: TextWatcher {
        override fun afterTextChanged(s: Editable) {
            afterTextChanged.invoke(s.toString())
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

    })
}