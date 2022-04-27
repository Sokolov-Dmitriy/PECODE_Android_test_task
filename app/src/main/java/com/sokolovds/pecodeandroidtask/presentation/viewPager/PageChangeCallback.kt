package com.sokolovds.pecodeandroidtask.presentation.viewPager

import androidx.viewpager2.widget.ViewPager2

class PageChangeCallback(private val listener: PageSelectedListener) :
    ViewPager2.OnPageChangeCallback() {
    interface PageSelectedListener {
        fun onSelected(position: Int)
    }

    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
        listener.onSelected(position)
    }
}