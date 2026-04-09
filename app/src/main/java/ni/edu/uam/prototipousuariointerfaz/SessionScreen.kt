package ni.edu.uam.prototipousuariointerfaz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val PsycheBlue = Color(0xFF2C4C9B)
private val PsycheInputGrey = Color(0xFFF2F2F2)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionScreen() {
    var userText by remember { mutableStateOf("") }
    var classification by remember { mutableStateOf<String?>(null) }
    var responseMessage by remember { mutableStateOf("") }
    var resultColor by remember { mutableStateOf(Color.Gray) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("PsycheAI", fontSize = 12.sp, color = Color.White.copy(alpha = 0.8f))
                        Text("Sesión de Análisis", fontWeight = FontWeight.Bold, color = Color.White, fontSize = 20.sp)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { /* Simular navegación */ }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PsycheBlue
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.White)
        ) {
            // Cabecera azul
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PsycheBlue)
                    .padding(horizontal = 24.dp, vertical = 24.dp)
            ) {
                Column {
                    Text(
                        text = "Bienvenido a tu sesión",
                        fontSize = 28.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Tu espacio seguro de autoconocimiento emocional",
                        fontSize = 14.sp,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                }
            }

            // Contenido
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "¿Qué situación te hizo sentir así hoy?",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                // Input
                TextField(
                    value = userText,
                    onValueChange = { userText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    placeholder = { Text("Escribe tus pensamientos aquí...") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = PsycheInputGrey,
                        unfocusedContainerColor = PsycheInputGrey,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    shape = MaterialTheme.shapes.medium
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Botón circular flotante simulado como en la imagen
                    Surface(
                        shape = CircleShape,
                        color = Color(0xFFF0F0F0),
                        modifier = Modifier.size(56.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.Menu, contentDescription = null, tint = Color.Black)
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    // Botón de Analizar
                    Button(
                        onClick = {
                            val text = userText.lowercase()
                            val negativeWords = listOf("odio", "mal", "triste", "estresado", "ansiedad", "feo", "solo", "cansado", "horrible")
                            val positiveWords = listOf("feliz", "bien", "alegre", "emocionado", "paz", "tranquilo", "logré", "bueno")

                            val negCount = negativeWords.count { text.contains(it) }
                            val posCount = positiveWords.count { text.contains(it) }

                            if (negCount > posCount) {
                                classification = "Negativo"
                                responseMessage = "Lamentamos que te sientas así. Recuerda que es válido tener días difíciles. ¿Quieres profundizar en por qué sientes esto?"
                                resultColor = Color(0xFFD32F2F) // Rojo
                            } else if (posCount > negCount) {
                                classification = "Positivo"
                                responseMessage = "¡Qué alegría! Mantener una mentalidad positiva fortalece tu bienestar emocional. ¡Sigue así!"
                                resultColor = Color(0xFF388E3C) // Verde
                            } else {
                                classification = "Neutral"
                                responseMessage = "Tus pensamientos reflejan un estado equilibrado o una observación objetiva de tu entorno."
                                resultColor = Color(0xFF757575) // Gris
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PsycheBlue),
                        shape = MaterialTheme.shapes.extraLarge
                    ) {
                        Text("Analizar Pensamiento", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Tarjeta de Resultados mejorada
                if (classification != null) {
                    ResultCard(
                        classification = classification!!,
                        message = responseMessage,
                        color = resultColor
                    )
                }
            }
        }
    }
}

@Composable
fun ResultCard(classification: String, message: String, color: Color) {
    val bgColor = when(classification) {
        "Positivo" -> Color(0xFFE8F5E9)
        "Negativo" -> Color(0xFFFFEBEE)
        else -> Color(0xFFF5F5F5)
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = MaterialTheme.shapes.large
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = "Resultado de PsycheAI",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Text(text = "Estado: ", fontWeight = FontWeight.Bold, color = Color.Black)
                Text(text = classification, fontWeight = FontWeight.Bold, color = color)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray,
                lineHeight = 20.sp
            )
        }
    }
}
