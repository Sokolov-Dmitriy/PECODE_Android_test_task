package com.sokolovds.pecodeandroidtask.presentation.viewPager

import androidx.viewpager2.widget.ViewPager2

fun ViewPager2.changeItemCount(itemCount: Int) {
    val adapter = this.adapter as PagerFragmentAdapter
    if (itemCount - currentItem == 0) {
        setCurrentItem(itemCount - 1, false)
    }
    adapter.itemCount = itemCount
}

val ViewPager2.currentAdapter: PagerFragmentAdapter
    get() = adapter as PagerFragmentAdapter