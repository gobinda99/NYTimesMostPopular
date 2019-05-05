package com.gobinda.mvp.sample.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nytimes.sample.data.model.Data
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Data access class Flight Event Model.
 * Insert, update, select, delete operation/query using RxJava so thread
 * scheduling, non block ui operation can be maintained easily.
 */
@Dao
interface NYNewDao {

    @Query("SELECT * FROM data")
    fun loadData(): Single<List<Data>>

    @Query("SELECT * from data where id = :id LIMIT 1")
    fun loadDataById(id: Int): Single<Data>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(flights: List<Data>): Completable

    @Query("DELETE FROM data")
    fun deleteAllData(): Completable
}