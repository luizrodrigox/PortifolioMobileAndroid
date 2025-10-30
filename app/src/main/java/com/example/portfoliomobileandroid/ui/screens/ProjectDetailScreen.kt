package com.example.portfoliomobileandroid.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfoliomobileandroid.ui.ProjectViewModel

@Composable
fun ProjectDetailScreen(
    projectId: Int,
    viewModel: ProjectViewModel
){
    val project by viewModel.getProjectDetailFlow(projectId).collectAsState(initial = null)

    Column(
        modifier = Modifier.fillMaxSize().padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Detalhes do Projeto",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )

        Spacer(modifier = Modifier.height(25.dp))

        project?.let {
            Column(
                modifier = Modifier.fillMaxSize().padding(start = 15.dp, end = 15.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = "ID (API): ${it.apiId}")
                Text(text = "Nome: ${it.name}", fontWeight = FontWeight.Bold)
                Text(text = "Descrição: ${it.description}")
                Text(text = "Linguagem: ${it.language}", modifier = Modifier.padding(top = 8.dp))
            }
        } ?: Text("Carregando detalhes ou projeto não encontrado...")
    }
}