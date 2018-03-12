package com.tabbottom

import android.content.res.ColorStateList
import android.graphics.drawable.RippleDrawable
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.common_tab_bottom)
        var v = findViewById<Button>(R.id.btn_1_1)
        if (Build.VERSION.SDK_INT >= 21) {
            v.setBackground(RippleDrawable(ColorStateList(arrayOf(intArrayOf()), intArrayOf(0xFFFFFF and 0x56000000 or 0x56000000)), null, null))
        } else {
            v.setBackgroundResource(R.drawable.material_item_background)
        }
    }
}
