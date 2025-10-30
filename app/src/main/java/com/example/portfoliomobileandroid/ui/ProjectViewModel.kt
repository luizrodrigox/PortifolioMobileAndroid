package com.example.portfoliomobileandroid.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.portfoliomobileandroid.data.ProjectRepository
import com.example.portfoliomobileandroid.data.local.Project
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class ProjectListUiState(
    val projects: List<Project> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class ProjectViewModel(
    private val repository: ProjectRepository
) : ViewModel() {

    private val _listUiState = MutableStateFlow(ProjectListUiState(isLoading = true))
    val listUiState: StateFlow<ProjectListUiState> = _listUiState.asStateFlow()

    private var initialLoadAttempted = false

    init {
        viewModelScope.launch {
            repository.projects
                .collect { projectsFromDb ->
                    _listUiState.update { currentState ->
                        currentState.copy(
                            projects = projectsFromDb,
                            isLoading = if (projectsFromDb.isNotEmpty()) false else currentState.isLoading
                        )
                    }
                    if(projectsFromDb.isEmpty() && !initialLoadAttempted){
                        initialLoadAttempted = true
                        refreshData(forceLoadingState = true)
                    }
                }
        }
    }

    fun refreshData(forceLoadingState: Boolean = false) {
        viewModelScope.launch {
            if (forceLoadingState || _listUiState.value.projects.isEmpty()) {
                _listUiState.update { it.copy(isLoading = true, errorMessage = null) }
            }

            try {
                repository.refreshProjects()
            } catch (e: Exception) {
                _listUiState.update { it.copy(
                    errorMessage = "Falha ao carregar: ${e.message}",
                    isLoading = false
                ) }
            } finally {
                if (_listUiState.value.projects.isEmpty()) {
                    _listUiState.update { it.copy(isLoading = false) }
                }
            }
        }
    }

    fun getProjectDetailFlow(apiId: Int): Flow<Project?> {
        return repository.getProjectDetails(apiId)
    }

    companion object {
        fun Factory(repository: ProjectRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(ProjectViewModel::class.java)) {
                        return ProjectViewModel(repository) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
    }
}