package com.sokolovds.pecodeandroidtask.domain.useCases

import com.sokolovds.pecodeandroidtask.domain.Repository

class DecrementCountUseCase (private val repository: Repository) {
    suspend operator fun invoke(){
        repository.decrementCount()
    }
}