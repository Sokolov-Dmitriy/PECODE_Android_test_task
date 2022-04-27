package com.sokolovds.pecodeandroidtask.data.preferencesDataSource

import android.content.SharedPreferences
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface PreferencesDataStore {

    suspend fun saveInt(key: String, value: Int)

    suspend fun loadInt(key: String, defaultValue: Int): Int

    class Base(
        private val sharedPreferences: SharedPreferences,
        private val dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) : PreferencesDataStore {
        override suspend fun saveInt(key: String, value: Int) = withContext(dispatcher) {
            val edit = sharedPreferences.edit()
            edit.putInt(key, value).apply()
        }

        override suspend fun loadInt(key: String, defaultValue: Int) = withContext(dispatcher) {
            return@withContext sharedPreferences.getInt(key, defaultValue)
        }
    }

}