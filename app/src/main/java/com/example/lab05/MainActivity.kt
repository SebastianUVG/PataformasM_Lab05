package com.example.lab05

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab05.ui.theme.Lab05Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab05Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}



@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(onDownloadClick = {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp")
            )
            context.startActivity(intent)
        })

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Domingo",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "14 de abril",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            Spacer(modifier = Modifier.width(16.dp))
            ActionButton(
                text = "Terminar jornada",

            )
        }


        Spacer(modifier = Modifier.height(16.dp))

        EventCard(
            title = "Vesuvio",
            address = "Paseo Cayalá, Blvr. Rafael Landivar 10-05",
            time = "10:00 AM - 10:00 PM",
            onStartClick = {
                Toast.makeText(context, "Sebastian Garcia Bustamante", Toast.LENGTH_SHORT).show()
            },
            onDetailsClick = {
                Toast.makeText(
                    context,
                    "Tipo de comida: Italiana\nPrecio: Q 256",
                    Toast.LENGTH_LONG
                ).show()
            },
            onLocationClick = {
                val gmmIntentUri = Uri.parse("geo:0,0?q=Paseo+Cayalá,+Blvr.+Rafael+Landivar+10-05")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                context.startActivity(mapIntent)
            }
        )
    }
}

@Composable
fun TopBar(onDownloadClick: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(start = 16.dp)
                .size(24.dp)
                .clip(CircleShape)
                .background(Color.Cyan )
                .padding(3.dp)


        ) {
            Image(
                painter = painterResource(id = R.drawable.icono1),
                contentDescription = "Custom Icon",
                modifier = Modifier.fillMaxSize()
            )
        }
        Text(
            text = "Actualización disponible",
            modifier = Modifier
                .padding(start = 8.dp, end = 16.dp)
                .weight(1f),
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        TextButton(onClick = onDownloadClick) {
            Text(text = "Descargar", color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Composable
fun ActionButton(text: String) {
    Button(
        onClick = { /* Acción del botón */ },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        ),
        modifier = Modifier
            .height(54.dp)
            .padding(top = 16.dp)
            ,
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(text = text)
    }
}

@Composable
fun EventCard(
    title: String,
    address: String,
    time: String,
    onStartClick: () -> Unit,
    onDetailsClick: () -> Unit,
    onLocationClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = onLocationClick) {
                    Image(
                        painter = painterResource(id = R.drawable.direccion2),
                        contentDescription = "Directions",
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Text(
                text = address,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = time,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Button(
                    onClick = onStartClick,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Iniciar")
                }
                Spacer(modifier = Modifier.width(8.dp))
                TextButton(onClick = onDetailsClick) {
                    Text(text = "Detalles", color = MaterialTheme.colorScheme.secondary)
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Lab05Theme {
        Surface(modifier = Modifier.fillMaxSize()) {
            MainScreen(modifier = Modifier.fillMaxSize())
        }
    }
}
