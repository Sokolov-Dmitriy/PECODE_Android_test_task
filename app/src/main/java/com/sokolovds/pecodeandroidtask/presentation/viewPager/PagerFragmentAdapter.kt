package com.sokolovds.pecodeandroidtask.presentation.viewPager

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerFragmentAdapter(activity: AppCompatActivity, startCountFragment: Int = 0) :
    FragmentStateAdapter(activity) {

    private var itemCount: Int = startCountFragment

    @SuppressLint("NotifyDataSetChanged")
    fun setItemCount(count: Int) {
        itemCount = count
        println("Pager:setItemCount:notifyDataSetChanged")
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return itemCount
    }

    override fun createFragment(position: Int): Fragment {
        println("Pager:createFragment")
        return PagerFragment.newInstance(position)
    }

}

