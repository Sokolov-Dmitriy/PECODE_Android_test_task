package com.sokolovds.pecodeandroidtask.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.sokolovds.pecodeandroidtask.databinding.ActivityMainBinding
import com.sokolovds.pecodeandroidtask.presentation.actions.UiActions
import com.sokolovds.pecodeandroidtask.presentation.actions.observeNonNull
import com.sokolovds.pecodeandroidtask.presentation.viewPager.PageChangeCallback
import com.sokolovds.pecodeandroidtask.presentation.viewPager.PagerFragmentAdapter
import com.sokolovds.pecodeandroidtask.presentation.viewPager.changeItemCount
import com.sokolovds.pecodeandroidtask.presentation.viewPager.currentAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<MainActivityViewModel>()

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setCurrentFragment(intent)
    }

    private fun handleUiActon(uiActions: UiActions) {
        when (uiActions) {
            is UiActions.RemoveNotification -> {
                val manager = NotificationManagerCompat.from(this)
                manager.cancel(uiActions.notificationId)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        initViewPager()
        setCurrentFragment(intent)

        observeData()
        observeUiEvent()
    }

    private fun initViewPager() {
        val adapter = PagerFragmentAdapter(this)
        binding.viewPager.adapter = adapter
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.viewPager.registerOnPageChangeCallback(getAdapterCallback())
    }

    private fun observeData() {
        lifecycleScope.launchWhenCreated {
            viewModel.fragmentCount.collect {
                if (binding.viewPager.currentAdapter.itemCount > it) viewModel.removeNotification(it)
                binding.viewPager.changeItemCount(it)
            }
        }

        lifecycleScope.launchWhenResumed {
            viewModel.currentFragmentPosition.collect {
                binding.viewPager.setCurrentItem(it, false)
            }
        }
    }

    private fun observeUiEvent() {
        viewModel.uiEvent.observeNonNull(this) {
            it.getContentIfNotHandled()?.let { uiCommand ->
                handleUiActon(uiCommand)
            }
        }
    }

    private fun setCurrentFragment(intent: Intent?) {
        if (intent != null) {
            viewModel.setCurrentPosition(intent.getIntExtra(NOTIFICATION_NAME, -1))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.viewPager.unregisterOnPageChangeCallback(getAdapterCallback())
    }

    private fun getAdapterCallback() = PageChangeCallback(viewModel)

    companion object {
        const val NOTIFICATION_NAME = "NOTIFICATION"
    }

}







