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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.portfoliomobileandroid.data.ProjectRepository
import com.example.portfoliomobileandroid.data.local.AppDatabase
import com.example.portfoliomobileandroid.data.local.Project
import com.example.portfoliomobileandroid.data.remote.RetrofitInstance
import com.example.portfoliomobileandroid.ui.ProjectViewModel
import com.example.portfoliomobileandroid.ui.screens.ProjectDetailScreen
import com.example.portfoliomobileandroid.ui.screens.ProjectListScreen
import com.example.portfoliomobileandroid.ui.theme.PortfolioMobileAndroidTheme

class MainActivity : ComponentActivity() {

    private lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        val database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "portfolio-db"
        ).build()

        val projectDao = database.projectDao()
        val apiService = RetrofitInstance.api

        val repository = ProjectRepository(apiService, projectDao)
        viewModelFactory = ProjectViewModel.Factory(repository)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PortfolioMobileAndroidTheme {
                AppNavigation(viewModelFactory)
            }
        }
    }
}

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
fun AppNavigation(viewModelFactory: ViewModelProvider.Factory){
    val navController = rememberNavController()
    val projectViewModel: ProjectViewModel = viewModel(factory = viewModelFactory)

    NavHost(navController = navController, startDestination = "profile"){

        composable("profile") {
            ProfileScreen(navController)
        }
        composable("project_list") {
            ProjectListScreen(navController, projectViewModel)
        }
        composable("project_detail/{projectId}"){
            backStackEntry ->
            val projectId = backStackEntry.arguments?.getString("projectId")?.toIntOrNull() ?: 0
            ProjectDetailScreen(projectId, projectViewModel)
        }

    }
}

val MockViewModelFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
        throw IllegalStateException("A Factory REAL não deve ser usada em Previews.")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PortfolioMobileAndroidTheme {
        AppNavigation(MockViewModelFactory)
    }
}