package com.sokolovds.pecodeandroidtask.domain.useCases

import com.sokolovds.pecodeandroidtask.domain.Repository

class FragmentCountFlowUseCase(private val repository: Repository) {
    operator fun invoke() = repository.fragmentCount
}