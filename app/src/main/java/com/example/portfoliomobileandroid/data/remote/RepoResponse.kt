package com.example.portfoliomobileandroid.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepoResponse(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("description") val description: String?,
    @SerialName("language") val language: String?
)