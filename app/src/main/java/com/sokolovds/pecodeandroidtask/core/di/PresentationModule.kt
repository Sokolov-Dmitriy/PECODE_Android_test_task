package com.sokolovds.pecodeandroidtask.core.di

import com.sokolovds.pecodeandroidtask.presentation.MainActivityViewModel
import com.sokolovds.pecodeandroidtask.presentation.viewPager.PagerFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appViewModels = module {
    viewModel {
        PagerFragmentViewModel(
            incrementCountUseCase = get(),
            decrementCountUseCase = get()
        )
    }

    viewModel {
        MainActivityViewModel(
            fragmentCountFlow = get(),
            currentPositionFlow = get(),
            savePositionUseCase = get(),
            setCurrentPositionUseCase = get()
        )
    }
}