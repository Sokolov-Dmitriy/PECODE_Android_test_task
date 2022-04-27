package com.sokolovds.pecodeandroidtask.domain

import kotlinx.coroutines.flow.Flow


interface Repository {
    val fragmentCount: Flow<Int>

    val currentFragment: Flow<Int>

    //устанавливаем позицию фрагмента, который должен отобраться во ViewPager
    suspend fun setCurrentPosition(position: Int)

    //сохраняем позицию активного фрагмента в dataStore
    suspend fun savePosition(position: Int)

    //Уменьшаем кол-во фрагментов во viewpager(на один элемент)
    suspend fun decrementCount()

    //Увеличиваем кол-во фрагментов во viewpager(на один элемент)
    suspend fun incrementCount()
}