package com.example.portfoliomobileandroid.data

import com.example.portfoliomobileandroid.data.local.Project
import com.example.portfoliomobileandroid.data.local.ProjectDao
import com.example.portfoliomobileandroid.data.remote.ApiService
import kotlinx.coroutines.flow.Flow

class ProjectRepository(
    private val apiService: ApiService,
    private val projectDao: ProjectDao,
    private val githubUser: String = "luizrodrigox"
) {
    val projects: Flow<List<Project>> = projectDao.getAllProjects()

    fun getProjectDetails(apiId: Int): Flow<Project?> {
        return projectDao.getProjectByApiId(apiId)
    }

    suspend fun refreshProjects() {
        try {
            val repos = apiService.listRepos(githubUser)

            val projectsToInsert = repos.map { repo ->
                Project(
                    apiId = repo.id,
                    name = repo.name,
                    description = repo.description ?: "Sem descrição.",
                    language = repo.language ?: "Desconhecida"
                )
            }

            projectDao.deleteAll()
            projectDao.insertAll(projectsToInsert)
        } catch (e: Exception) {
            println("Erro ao sincronizar projetos: ${e.message}")
            throw e
        }
    }
}