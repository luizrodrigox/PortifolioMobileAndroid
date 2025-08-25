package com.example.portfoliomobileandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfoliomobileandroid.ui.theme.PortfolioMobileAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PortfolioMobileAndroidTheme {
                Home()
            }
        }
    }
}

@Composable
fun Home() {

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
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PortfolioMobileAndroidTheme {
        Home()
    }
}