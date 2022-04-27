package com.sokolovds.pecodeandroidtask.domain.useCases

import com.sokolovds.pecodeandroidtask.domain.Repository

class SetCurrentPositionUseCase(private val repository: Repository) {
    suspend operator fun invoke(position: Int) {
        repository.setCurrentPosition(position)
    }
}