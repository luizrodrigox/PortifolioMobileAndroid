package com.example.portfoliomobileandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.portfoliomobileandroid.ui.theme.PortfolioMobileAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PortfolioMobileAndroidTheme {
                AppNavigation()
            }
        }
    }
}

data class Project(
    val id: Int,
    val name: String,
    val description: String,
    val language: String
)

val projectList = listOf(
    Project(1, "Portfólio Mobile", "Aplicativo de cartão de visitas digital.", "Kotlin"),
    Project(2, "Sistema de Gerenciamento de Pousada", "Sistema criado para gerenciamento de uma pousada.", "Java"),
    Project(3, "E-commerce", "Protótipo de loja virtual mobile.", "Kotlin"),
    Project(4, "Clone Netflix", "Site de streaming para estudos.", "JavaScript"),
    Project(5, "Gestor de Tarefas", "Aplicativo para organizar atividades diárias.", "C"),
    Project(6, "Quiz Interativo", "Jogo de perguntas e respostas com pontuação.", "Python"),
    Project(7, "App de Receitas", "Catálogo de receitas com imagens e ingredientes.", "JavaScript"),
    Project(8, "Finanças Pessoais", "Controle de gastos com gráficos e relatórios.", "Swift"),
    Project(9, "Agenda de Contatos", "Aplicativo CRUD de contatos com banco local.", "Kotlin"),
    Project(10, "Clima Hoje", "Aplicativo de previsão do tempo com dados mockados.", "Java")
)

@Composable
fun ProjectCard(project: Project, onClick: () -> Unit){
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp).clickable{onClick()},
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = project.name,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = project.description,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun ProfileScreen(navController: NavHostController){
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {
        Row(
            modifier = Modifier.background(Color.Black).fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.fotoperfil),
                contentDescription = ("Foto de Perfil"),
                modifier = Modifier.padding(10.dp).size(150.dp).clip(CircleShape)
            )
        }

        Row(
            modifier = Modifier.background(Color.Black).fillMaxWidth()
        ) {
            Text(
                text = "Luiz Rodrigo Melo de Freitas Junior",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier.padding(15.dp, 15.dp, 0.dp, 5.dp)
            )
        }

        Row(
            modifier = Modifier.background(Color.Black).fillMaxWidth()
        ) {
            Text(
                text = "Tecnólogo em Sistemas para Internet",
                color = Color.White,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                modifier = Modifier.padding(15.dp, 5.dp, 0.dp, 30.dp)
            )
        }

        Spacer(modifier = Modifier.height(25.dp).background(Color.Black))

        Row(
            modifier = Modifier.padding(15.dp, 0.dp, 0.dp, 5.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.iconetelefone),
                contentDescription = ("Ícone de Telefone"),
                modifier = Modifier.padding(end = 5.dp)
            )

            Text(
                text = "(84)99701-1410",
                fontSize = 16.sp)

        }

        Row(
            modifier = Modifier.padding(15.dp, 5.dp, 0.dp, 5.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.iconeemail),
                contentDescription = ("Ícone de Email"),
                modifier = Modifier.padding(end = 5.dp)
            )

            Text(
                text = "melo9657@gmail.com",
                fontSize = 16.sp
            )
        }

        Row(
            modifier = Modifier.padding(15.dp, 5.dp, 0.dp, 5.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.iconelinkedin),
                contentDescription = ("Ícone do LinkedIn"),
                modifier = Modifier.padding(end = 5.dp)
            )

            Text(
                text = "Luiz Rodrigo",
                fontSize = 16.sp
            )
        }

        Row(
            modifier = Modifier.padding(15.dp, 5.dp, 0.dp, 5.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.iconegithub),
                contentDescription = ("Ícone do GitHub"),
                modifier = Modifier.padding(end = 5.dp)
            )

            Text(
                text = "luizrodrigox",
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Button(onClick = { navController.navigate("project_list") }) {
                Text(text = "Meus Projetos")
            }
        }
    }
}

@Composable
fun ProjectListScreen(navController: NavHostController){
    LazyColumn(modifier = Modifier.padding(start = 15.dp, top = 40.dp, end = 15.dp)) {
        items(projectList) { project ->
            ProjectCard(project = project) {
                navController.navigate("project_detail/${project.id}")
            }
        }
    }
}

@Composable
fun ProjectDetailScreen(projectId: Int){
    val project = projectList.find { it.id == projectId }
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
                Text(text = "ID: ${it.id}")
                Text(text = "Nome: ${it.name}")
                Text(text = "Descrição: ${it.description}")
                Text(text = "Linguagem: ${it.language}")
            }
        }
    }
}

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "profile"){

        composable("profile") {
            ProfileScreen(navController)
        }
        composable("project_list") {
            ProjectListScreen(navController)
        }
        composable("project_detail/{projectId}"){
            backStackEntry ->
            val projectId = backStackEntry.arguments?.getString("projectId")?.toIntOrNull() ?: 0
            ProjectDetailScreen(projectId)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PortfolioMobileAndroidTheme {
        AppNavigation()
    }
}