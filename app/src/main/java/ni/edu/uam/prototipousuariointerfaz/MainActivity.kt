package ni.edu.uam.prototipousuariointerfaz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ni.edu.uam.prototipousuariointerfaz.ui.theme.PrototipoUsuarioInterfazTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrototipoUsuarioInterfazTheme {
                LoginScreen()
            }
        }
    }
}

@Composable
fun LoginScreen() {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        // 🔵 HEADER CON GRADIENTE
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF3F2B96),
                            Color(0xFF5F6BEA)
                        )
                    )
                )
                .padding(24.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Column {
                Text(
                    text = "PsycheAI",
                    color = Color.White,
                    fontSize = 22.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Bienvenido de nuevo",
                    color = Color.White,
                    fontSize = 26.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Tu espacio seguro de autoconocimiento emocional",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 14.sp
                )
            }
        }

        // ⚪ CONTENIDO
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            // EMAIL
            Text(text = "Correo electrónico", color = Color.Gray)
            TextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("usuario@email.com") },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))

            // PASSWORD
            Text(text = "Contraseña", color = Color.Gray)
            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("********") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "¿Olvidaste tu contraseña?",
                color = Color(0xFF5F6BEA)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // BOTÓN LOGIN
            Button(
                onClick = {
                    if (email == "admin@psycheai.com" && password == "1234") {
                        mensaje = "Inicio de sesión exitoso ✅"
                    } else {
                        mensaje = "Datos incorrectos ❌"
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5F6BEA)
                ),
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                Text("Iniciar sesión")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // MENSAJE
            Text(text = mensaje)

            Spacer(modifier = Modifier.height(20.dp))

            // TEXTO FINAL
            Text(
                text = "¿No tienes cuenta? Regístrate",
                color = Color.Gray
            )
        }
    }
}