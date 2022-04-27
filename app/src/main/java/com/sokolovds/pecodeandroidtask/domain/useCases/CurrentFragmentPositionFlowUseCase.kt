package com.sokolovds.pecodeandroidtask.domain.useCases

import com.sokolovds.pecodeandroidtask.domain.Repository

class CurrentFragmentPositionFlowUseCase(private val repository: Repository) {
    operator fun invoke() = repository.currentFragment
}