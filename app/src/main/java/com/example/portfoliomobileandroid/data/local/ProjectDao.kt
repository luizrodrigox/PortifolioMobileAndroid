package com.example.portfoliomobileandroid.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {
    @Query("SELECT * FROM projects ORDER BY api_id ASC")
    fun getAllProjects(): Flow<List<Project>>

    @Query("SELECT * FROM projects WHERE api_id = :apiId")
    fun getProjectByApiId(apiId: Int): Flow<Project?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(projects: List<Project>)

    @Query("DELETE FROM projects")
    suspend fun deleteAll()
}