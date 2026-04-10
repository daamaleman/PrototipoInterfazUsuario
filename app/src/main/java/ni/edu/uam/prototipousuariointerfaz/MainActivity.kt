package ni.edu.uam.prototipousuariointerfaz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import ni.edu.uam.prototipousuariointerfaz.ui.theme.PrototipoUsuarioInterfazTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrototipoUsuarioInterfazTheme {
                AppEntryPoint()
            }
        }
    }
}

private enum class AppScreen {
    Login,
    Register,
    Main
}

@Composable
fun AppEntryPoint() {
    var currentScreen by remember { mutableStateOf(AppScreen.Login) }

    when (currentScreen) {
        AppScreen.Login -> LoginScreen(
            onLoginSuccess = { currentScreen = AppScreen.Main },
            onGoToRegister = { currentScreen = AppScreen.Register }
        )

        AppScreen.Register -> RegisterScreen(
            onRegisterSuccess = { currentScreen = AppScreen.Main },
            onBackToLogin = { currentScreen = AppScreen.Login }
        )

        AppScreen.Main -> MainScreen(onLogout = { currentScreen = AppScreen.Login })
    }
}
