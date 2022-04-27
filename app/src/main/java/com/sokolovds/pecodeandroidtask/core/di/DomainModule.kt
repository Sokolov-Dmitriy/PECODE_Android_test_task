package com.sokolovds.pecodeandroidtask.core.di

import com.sokolovds.pecodeandroidtask.domain.useCases.*
import org.koin.dsl.module

val domainModule = module {
    factory<FragmentCountFlowUseCase> {
        FragmentCountFlowUseCase(repository = get())
    }

    factory<CurrentFragmentPositionFlowUseCase> {
        CurrentFragmentPositionFlowUseCase(repository = get())
    }

    factory<DecrementCountUseCase> {
        DecrementCountUseCase(repository = get())
    }

    factory<IncrementCountUseCase> {
        IncrementCountUseCase(repository = get())
    }

    factory<SavePositionUseCase> {
        SavePositionUseCase(repository = get())
    }

    factory<SetCurrentPositionUseCase> {
        SetCurrentPositionUseCase(repository = get())
    }
}