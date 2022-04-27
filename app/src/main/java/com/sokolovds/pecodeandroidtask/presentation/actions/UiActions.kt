package com.sokolovds.pecodeandroidtask.presentation.actions

sealed class UiActions {
    data class RemoveNotification (val notificationId:Int) :UiActions()
}
