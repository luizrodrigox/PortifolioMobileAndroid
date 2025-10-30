package com.example.portfoliomobileandroid.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "projects")
data class Project(
    @PrimaryKey(autoGenerate =  true)
    val id: Int = 0,
    @ColumnInfo(name = "api_id")
    val apiId: Int = 0,
    @ColumnInfo(name = "project_name")
    val name: String,
    @ColumnInfo(name = "project_description")
    val description: String,
    @ColumnInfo(name = "project_language")
    val language: String
)