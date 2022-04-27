package com.sokolovds.pecodeandroidtask.data

import com.sokolovds.pecodeandroidtask.data.preferencesDataSource.PreferencesConstantValue
import com.sokolovds.pecodeandroidtask.domain.Repository
import com.sokolovds.pecodeandroidtask.data.preferencesDataSource.PreferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking


class RepositoryImp(
    private val preferencesDataStore: PreferencesDataStore
) : Repository {
    private val _fragmentCount = MutableStateFlow(runBlocking {
        val count = preferencesDataStore.loadInt(PreferencesConstantValue.FRAGMENT_COUNT, 1)
        if (count < 1) 1
        else count
    })
    override val fragmentCount: Flow<Int> = _fragmentCount

    private val _currentFragment = MutableStateFlow(runBlocking {
        preferencesDataStore.loadInt(PreferencesConstantValue.CURRENT_ACTIVE_FRAGMENT_POSITION, 0)
    })
    override val currentFragment: Flow<Int> = _currentFragment

    override suspend fun setCurrentPosition(position: Int) {
        if (position in 0 until _fragmentCount.value) {
            _currentFragment.value = position
            savePosition(position)
        }
    }

    override suspend fun savePosition(position: Int) {
        preferencesDataStore.saveInt(
            PreferencesConstantValue.CURRENT_ACTIVE_FRAGMENT_POSITION,
            position
        )
    }

    override suspend fun decrementCount() {
        if (_fragmentCount.value > 1) {
            _fragmentCount.value -= 1
            preferencesDataStore.saveInt(
                PreferencesConstantValue.FRAGMENT_COUNT,
                _fragmentCount.value
            )
        }
    }

    override suspend fun incrementCount() {
        _fragmentCount.value += 1
        preferencesDataStore.saveInt(PreferencesConstantValue.FRAGMENT_COUNT, _fragmentCount.value)
    }

}