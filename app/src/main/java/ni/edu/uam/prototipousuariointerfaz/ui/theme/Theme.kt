package ni.edu.uam.prototipousuariointerfaz.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val PsycheBlue = Color(0xFF2C4C9B)
val PsycheInputGrey = Color(0xFFF2F2F2)

private val LightColorScheme = lightColorScheme(
    primary = PsycheBlue,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFDCE4FF),
    onPrimaryContainer = Color(0xFF001A42),
    secondary = Color(0xFF5B6FB5),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFE4E9FF),
    onSecondaryContainer = Color(0xFF171D4A),
    tertiary = Color(0xFF6C8CEB),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFE0E6FF),
    onTertiaryContainer = Color(0xFF001B3E),
    background = Color(0xFFF7F8FC),
    onBackground = Color(0xFF1A1A1A),
    surface = Color.White,
    onSurface = Color(0xFF1A1A1A),
    surfaceVariant = Color(0xFFE7E0EC),
    onSurfaceVariant = Color(0xFF49454F),
    error = Color(0xFFD32F2F),
    onError = Color.White,
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410E0B)
)

@Composable
fun PrototipoUsuarioInterfazTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content
    )
}

