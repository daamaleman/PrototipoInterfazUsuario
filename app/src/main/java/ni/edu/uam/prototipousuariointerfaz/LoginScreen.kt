package ni.edu.uam.prototipousuariointerfaz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF3F2B96), Color(0xFF5F6BEA))
                    )
                )
                .padding(24.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Column {
                Text(text = "PsycheAI", color = Color.White, fontSize = 22.sp)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Bienvenido de nuevo", color = Color.White, fontSize = 26.sp)
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Tu espacio seguro de autoconocimiento emocional",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 14.sp
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Correo electronico", color = Color.Gray)
            TextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("usuario@email.com") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Contrasena", color = Color.Gray)
            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("********") },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Olvidaste tu contrasena?", color = Color(0xFF5F6BEA))

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (email == "admin@psycheai.com" && password == "1234") {
                        mensaje = "Inicio de sesion exitoso"
                        onLoginSuccess()
                    } else {
                        mensaje = "Datos incorrectos"
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5F6BEA)),
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                Text("Iniciar sesion")
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = mensaje)

            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "No tienes cuenta? Registrate", color = Color.Gray)
        }
    }
}
