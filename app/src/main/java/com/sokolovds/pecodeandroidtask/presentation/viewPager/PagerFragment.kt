package com.sokolovds.pecodeandroidtask.presentation.viewPager

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationManagerCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import com.sokolovds.pecodeandroidtask.presentation.MainActivity
import com.sokolovds.pecodeandroidtask.R
import com.sokolovds.pecodeandroidtask.presentation.base.BaseFragment
import com.sokolovds.pecodeandroidtask.databinding.MainFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

class PagerFragment() : BaseFragment<MainFragmentBinding>() {

    private val viewModel by viewModel<PagerFragmentViewModel>()
    var position by Delegates.notNull<Int>()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): MainFragmentBinding = MainFragmentBinding.inflate(inflater, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        position = requireArguments().getInt(NUMBER_KEY)
        initView()

        binding.plusBtn.setOnClickListener {
            viewModel.onAddFragmentPressed()
        }
        binding.minusBtn.setOnClickListener {
            viewModel.onDeleteFragmentPressed()
        }

        binding.createNotificationBtn.setOnClickListener {
            buildNotification()
        }


    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        binding.pageNumber.text = (position + 1).toString()
        binding.minusBtn.isVisible = position > 0
    }

    private fun createPendingIntent(): PendingIntent {
        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        intent.putExtra(MainActivity.NOTIFICATION_NAME, position)
        return PendingIntent.getActivity(
            requireContext(),
            position,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun buildNotification() {
        val notification = buildNotification(
            getString(R.string.notification_title),
            getString(R.string.notification_description, position + 1)
        ).setContentIntent(createPendingIntent())
        with(NotificationManagerCompat.from(requireContext())) {
            notify(position, notification.build())
        }
    }


    companion object {
        fun newInstance(number: Int): PagerFragment {
            val fragment = PagerFragment()
            fragment.arguments = bundleOf(NUMBER_KEY to number)
            return fragment
        }
    }
}