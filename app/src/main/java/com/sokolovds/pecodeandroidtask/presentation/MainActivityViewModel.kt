package com.sokolovds.pecodeandroidtask.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sokolovds.pecodeandroidtask.domain.useCases.CurrentFragmentPositionFlowUseCase
import com.sokolovds.pecodeandroidtask.domain.useCases.FragmentCountFlowUseCase
import com.sokolovds.pecodeandroidtask.domain.useCases.SavePositionUseCase
import com.sokolovds.pecodeandroidtask.domain.useCases.SetCurrentPositionUseCase
import com.sokolovds.pecodeandroidtask.presentation.actions.Event
import com.sokolovds.pecodeandroidtask.presentation.actions.UiActions
import com.sokolovds.pecodeandroidtask.presentation.viewPager.PageChangeCallback
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class MainActivityViewModel(
    private val fragmentCountFlow: FragmentCountFlowUseCase,
    private val currentPositionFlow: CurrentFragmentPositionFlowUseCase,
    private val setCurrentPositionUseCase: SetCurrentPositionUseCase,
    private val savePositionUseCase: SavePositionUseCase
) : ViewModel(), PageChangeCallback.PageSelectedListener {



    private val _uiEvent = MutableLiveData<Event<UiActions>>()
    val uiEvent = _uiEvent

    fun removeNotification(notificationId: Int) {
        _uiEvent.value = Event(UiActions.RemoveNotification(notificationId))
    }


    private val _fragmentCount = MutableStateFlow(1)
    val fragmentCount: StateFlow<Int> = _fragmentCount

    private val _currentFragmentPosition = MutableStateFlow(0)
    val currentFragmentPosition: StateFlow<Int> = _currentFragmentPosition


    init {
        viewModelScope.launch {
            fragmentCountFlow().collect {
                _fragmentCount.emit(it)
            }
        }
        viewModelScope.launch {
            currentPositionFlow().collect {
                _currentFragmentPosition.emit(it)
            }
        }
    }

    fun setCurrentPosition(position: Int) {
        viewModelScope.launch {
            setCurrentPositionUseCase(position)
        }
    }

    override fun onSelected(position: Int) {
        viewModelScope.launch {
            savePositionUseCase(position)
        }
    }


}