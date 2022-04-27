package com.sokolovds.pecodeandroidtask.core.di

import android.content.Context
import com.sokolovds.pecodeandroidtask.data.RepositoryImp
import com.sokolovds.pecodeandroidtask.data.preferencesDataSource.PreferencesConstantValue
import com.sokolovds.pecodeandroidtask.data.preferencesDataSource.PreferencesDataStore
import com.sokolovds.pecodeandroidtask.domain.Repository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    factory<PreferencesDataStore> {
        PreferencesDataStore.Base(
            sharedPreferences = androidContext().getSharedPreferences(
                PreferencesConstantValue.PREFERENCES_NAME,
                Context.MODE_PRIVATE
            )
        )
    }

    single<Repository> {
        RepositoryImp(
            preferencesDataStore = get()
        )
    }
}