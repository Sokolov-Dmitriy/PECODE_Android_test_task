package com.sokolovds.pecodeandroidtask.domain.useCases

import com.sokolovds.pecodeandroidtask.domain.Repository

class IncrementCountUseCase(private val repository: Repository) {
    suspend operator fun invoke(){
        repository.incrementCount()
    }
}