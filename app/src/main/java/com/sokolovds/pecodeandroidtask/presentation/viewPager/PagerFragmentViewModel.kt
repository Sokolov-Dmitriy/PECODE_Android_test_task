package com.sokolovds.pecodeandroidtask.presentation.viewPager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sokolovds.pecodeandroidtask.domain.useCases.DecrementCountUseCase
import com.sokolovds.pecodeandroidtask.domain.useCases.IncrementCountUseCase
import kotlinx.coroutines.launch

class PagerFragmentViewModel(
    private val decrementCountUseCase: DecrementCountUseCase,
    private val incrementCountUseCase: IncrementCountUseCase
):ViewModel() {

    fun onAddFragmentPressed(){
        viewModelScope.launch {
            incrementCountUseCase()
        }

    }

    fun onDeleteFragmentPressed(){
        viewModelScope.launch {
            decrementCountUseCase()
        }

    }
}