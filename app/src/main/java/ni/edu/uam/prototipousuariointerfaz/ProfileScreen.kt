package ni.edu.uam.prototipousuariointerfaz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onLogout: () -> Unit = {}
) {

    val name = "David"
    val stress = 65
    val thoughts = 4
    val progress = 10

    val stressColor = when {
        stress < 40 -> Color(0xFF4CAF50)
        stress < 70 -> Color(0xFFFFC107)
        else -> Color(0xFFF44336)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        EmotionalHeader(name, stress, stressColor)

        PsychologicalAnalysis(stress, thoughts, progress)

        PatternSection()

        RecommendationCard(stress)

        ReprogramButton()

        SessionList()

        SettingsSection(onLogout = onLogout)
    }
}

// ---------------- HEADER EMOCIONAL ----------------

@Composable
fun EmotionalHeader(name: String, stress: Int, color: Color) {

    val stateText = when {
        stress < 40 -> "Estable"
        stress < 70 -> "Estrés leve"
        else -> "Alto estrés"
    }

    val message = when {
        stress < 40 -> "Hoy te encuentras emocionalmente equilibrado"
        stress < 70 -> "Hoy has mostrado señales de sobrecarga mental"
        else -> "Tu nivel de estrés es alto, considera hacer una pausa"
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color.White)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(name, color = Color.White, style = MaterialTheme.typography.titleLarge)

        Text("Estado emocional: $stateText", color = Color.White)

        Text(message, color = Color.White, style = MaterialTheme.typography.bodySmall)
    }
}

// ---------------- ANÁLISIS PSICOLÓGICO ----------------

@Composable
fun PsychologicalAnalysis(stress: Int, thoughts: Int, progress: Int) {

    val insight = when {
        stress < 40 -> "Tu estabilidad emocional ha sido constante"
        stress < 70 -> "Tu nivel de estrés ha aumentado esta semana"
        else -> "Se detecta un incremento significativo de estrés"
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp)
    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            Text("Análisis psicológico", style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(8.dp))

            Text("Nivel de estrés: $stress%")
            LinearProgressIndicator(progress = { stress / 100f })

            Spacer(modifier = Modifier.height(8.dp))

            Text("Pensamientos negativos detectados: $thoughts")

            Spacer(modifier = Modifier.height(8.dp))

            Text("Progreso emocional semanal: +$progress%")

            Spacer(modifier = Modifier.height(8.dp))

            Text(insight)
        }
    }
}

// ---------------- PATRONES DETECTADOS ----------------

@Composable
fun PatternSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text("Patrones detectados", style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(8.dp))

            Text("• Pensamiento negativo recurrente")
            Text("• Generalización: 'todo me sale mal'")
        }
    }
}

// ---------------- RECOMENDACIONES ----------------

@Composable
fun RecommendationCard(stress: Int) {

    val message = when {
        stress < 40 -> "Sigue reforzando tus pensamientos positivos"
        stress < 70 -> "Has mostrado pensamientos repetitivos negativos. Intenta cuestionarlos."
        else -> "Te recomendamos hablar con alguien de confianza o tomar un descanso"
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(message, modifier = Modifier.padding(16.dp))
    }
}

// ---------------- BOTÓN PRINCIPAL ----------------

@Composable
fun ReprogramButton() {
    Button(
        onClick = { /* navegación después */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Iniciar reprogramación cognitiva")
    }
}

// ---------------- HISTORIAL ----------------

@Composable
fun SessionList() {
    Column(modifier = Modifier.padding(8.dp)) {
        SessionItem("Hoy", "Reflexión emocional", "Ansiedad moderada")
        SessionItem("Ayer", "Escenario social", "Estado estable")
    }
}

@Composable
fun SessionItem(date: String, type: String, state: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(date)
            Text(type)
            Text("Estado: $state")
        }
    }
}

// ---------------- CONFIGURACIÓN ----------------

@Composable
fun SettingsSection(onLogout: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {

        Text("Configuración", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(8.dp))

        Text("Notificaciones")
        Text("Modo oscuro")
        Text("Privacidad")

        Spacer(modifier = Modifier.height(12.dp))

        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onLogout, modifier = Modifier.fillMaxWidth()) {
            Text("Cerrar sesion")
        }
    }
}