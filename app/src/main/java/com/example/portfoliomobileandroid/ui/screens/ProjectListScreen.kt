package com.example.portfoliomobileandroid.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.portfoliomobileandroid.ProjectCard
import com.example.portfoliomobileandroid.ui.ProjectViewModel

@Composable
fun ProjectListScreen(
    navController: NavHostController,
    viewModel: ProjectViewModel
) {
    val uiState by viewModel.listUiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp, vertical = 40.dp)
    ) {
        if (uiState.isLoading && uiState.projects.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
                Text("Buscando projetos do GitHub...", modifier = Modifier.padding(top = 80.dp))
            }
        } else if (uiState.errorMessage != null) {
            Column(
                modifier = Modifier.fillMaxSize().align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "Erro: ${uiState.errorMessage}",
                    color = Color.Red,
                    modifier = Modifier.padding(16.dp)
                )
                Button(
                    onClick = { viewModel.refreshData() },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Tentar Novamente")
                }
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(uiState.projects) { project ->
                    ProjectCard(project = project) {
                        navController.navigate("project_detail/${project.apiId}")
                    }
                }
            }
        }
    }
}