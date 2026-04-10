package ni.edu.uam.prototipousuariointerfaz

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 1. ESTRUCTURA PRINCIPAL DE LA PANTALLA (Adopta el diseño de cabecera de la imagen)
@Composable
fun ResultadosDashboardScreen(onGoToSession: () -> Unit = {}) {
    // ESTADO: Parámetro interactivo para simulación de recomendaciones
    var estresPercibido by remember { mutableFloatStateOf(75f) } // Initial estimated value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // Para pantallas más pequeñas
    ) {
        // A. PANEL DE CABECERA AZUL (Réplica del estilo de image_1.png)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF2C4C9B)) // Color azul del diseño de inicio
                .padding(24.dp)
                .padding(top = 16.dp)
        ) {
            Text(
                text = "PsycheAI",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Tu Resumen Semanal",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 40.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Visión general de tus sesiones y recomendaciones inteligentes basadas en tus patrones.",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 22.sp
            )
        }

        // B. ÁREA DE CONTENIDO (Fondo claro, estructurado con Cards)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // SECCIÓN 1: PERFIL EMOCIONAL DINÁMICO
            SectionHeader(title = "Perfil Psicológico Dinámico", icon = Icons.Default.Favorite)

            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Estado Emocional Dominante (Dato plausible)
                DataCard(
                    title = "Estado Emocional Dominante",
                    value = "Ansiedad Leve",
                    icon = Icons.Default.Favorite,
                    valueColor = MaterialTheme.colorScheme.error,
                    detailText = "Con picos de estrés elevados durante 3 sesiones."
                )

                // Nivel de Estrés Estimado (Visualización interactiva)
                StressGaugeCard(level = estresPercibido)
            }

            Spacer(modifier = Modifier.height(32.dp))

            // SECCIÓN 2: ANÁLISIS DE PATRONES DE PENSAMIENTO (LO MÁS INNOVADOR)
            SectionHeader(title = "Análisis de Pensamiento", icon = Icons.Default.Warning)

            // Lista de Distorsiones Cognitivas Detectadas (Dato plausible detallado)
            ThoughtPatternCard(
                generationsCount = 4,
                catastrophizingCount = 2,
                detailText = "Ejemplo detectado: 'Todo me sale mal' (Sección: Exámenes)."
            )

            Spacer(modifier = Modifier.height(32.dp))

            // SECCIÓN 3: EVOLUCIÓN SEMANAL Y SIMULACIÓN INTERACTIVA
            SectionHeader(title = "Evolución y Recomendaciones", icon = Icons.Default.Favorite)

            EvolutionSummaryCard()

            Spacer(modifier = Modifier.height(24.dp))

            // COMPONENTE INTERACTIVO DE SIMULACIÓN (Usabilidad extra para la rúbrica)
            SimulationControl(value = estresPercibido, onValueChange = { estresPercibido = it })

            Spacer(modifier = Modifier.height(24.dp))

            // LISTA DE RECOMENDACIONES INTELIGENTES (Dinámica basada en el estado)
            IntelligentRecommendationsList(stressLevel = estresPercibido)

            Spacer(modifier = Modifier.height(32.dp))

            // BOTÓN DE ACCIÓN (Para continuar el flujo)
            Button(
                onClick = onGoToSession,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Iniciar Nueva Reflexión", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// 2. COMPONENTES MODULARIZADOS (Demuestra buena estructura de código)

@Composable
fun SectionHeader(title: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(28.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun DataCard(
    title: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    valueColor: Color = MaterialTheme.colorScheme.onSurface,
    detailText: String = ""
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = title, style = MaterialTheme.typography.labelSmall)
                Text(text = value, style = MaterialTheme.typography.titleLarge, color = valueColor, fontWeight = FontWeight.Bold)
                if (detailText.isNotEmpty()) {
                    Text(text = detailText, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
    }
}

@Composable
fun StressGaugeCard(level: Float) {
    val levelText = when {
        level <= 40f -> "Bajo"
        level <= 70f -> "Medio"
        else -> "Alto"
    }
    val gaugeColor = when {
        level <= 40f -> MaterialTheme.colorScheme.primary
        level <= 70f -> Color(0xFFFFA000) // Ámbar
        else -> MaterialTheme.colorScheme.error
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Nivel de Estrés Estimado", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                LinearProgressIndicator(
                    progress = { level / 100f },
                    modifier = Modifier.weight(1f).height(12.dp).clip(CircleShape),
                    color = gaugeColor,
                    trackColor = MaterialTheme.colorScheme.surface
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "${level.toInt()}% - $levelText",
                    style = MaterialTheme.typography.labelMedium,
                    color = gaugeColor,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun ThoughtPatternCard(
    generationsCount: Int,
    catastrophizingCount: Int,
    detailText: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Detección de Distorsiones Cognitivas", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))

            // Item 1: Generalizaciones
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Warning, contentDescription = null, tint = MaterialTheme.colorScheme.error, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = "Generalizaciones Detectadas: $generationsCount", style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Item 2: Catastrofismo
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Warning, contentDescription = null, tint = MaterialTheme.colorScheme.error, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = "Patrones de Catastrofismo: $catastrophizingCount", style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(12.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Favorite, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = detailText, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

@Composable
fun EvolutionSummaryCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.Warning, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Tu nivel de estrés promedio ha bajado un 10% en comparación con la semana pasada.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

// 3. COMPONENTES INTERACTIVOS DE SIMULACIÓN (Usabilidad extra para la rúbrica)

@Composable
fun SimulationControl(value: Float, onValueChange: (Float) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Herramienta de Simulación (Para tu Video)", style = MaterialTheme.typography.labelSmall, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Ajusta tu estrés percibido para probar la lógica:", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(12.dp))
            Slider(
                value = value,
                onValueChange = onValueChange,
                valueRange = 0f..100f,
                steps = 9
            )
            Text(text = "Nivel Ajustado: ${value.toInt()}%", modifier = Modifier.align(Alignment.End), color = MaterialTheme.colorScheme.primary)
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun IntelligentRecommendationsList(stressLevel: Float) {
    // ESTADO: Lógica de selección de recomendaciones dinámica basada en el estado
    val dynamicRecommendations = remember(stressLevel) {
        when {
            stressLevel <= 40f -> listOf(
                Pair("Continúa con tus sesiones diarias de reflexión.", Icons.Default.Favorite),
                Pair("Prioriza actividades que te generen satisfacción este fin de semana.", Icons.Default.Favorite)
            )
            stressLevel <= 70f -> listOf(
                Pair("Dedica 10 minutos a ejercicios de respiración guiada hoy.", Icons.Default.Warning),
                Pair("Has mostrado patrones de generalización. Practica la identificación de evidencias positivas.", Icons.Default.Favorite)
            )
            else -> listOf(
                Pair("¡Nivel crítico de estrés detectado! Te recomendamos descanso absoluto prioritario.", Icons.Default.Warning),
                Pair("Agenda una actividad relajante o habla con alguien de confianza pronto.", Icons.Default.Favorite),
                Pair("Usa el modo 'Reprogramación Mental' para tus pensamientos negativos recurrentes.", Icons.Default.Warning)
            )
        }
    }

    AnimatedContent(
        targetState = dynamicRecommendations,
        transitionSpec = {
            fadeIn() togetherWith fadeOut()
        },
        label = "RecommendationsTransition"
    ) { recommendations ->
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            recommendations.forEach { recommendation ->
                RecommendationCard(text = recommendation.first, icon = recommendation.second)
            }
        }
    }
}

@Composable
fun RecommendationCard(text: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = text, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}