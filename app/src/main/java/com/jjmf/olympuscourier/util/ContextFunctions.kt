package com.jjmf.olympuscourier.util

import android.content.Context
import android.widget.Toast

fun Context.show(text:String?){
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}