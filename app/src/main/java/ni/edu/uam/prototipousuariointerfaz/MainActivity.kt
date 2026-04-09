package ni.edu.uam.prototipousuariointerfaz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ni.edu.uam.prototipousuariointerfaz.ui.theme.PrototipoUsuarioInterfazTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrototipoUsuarioInterfazTheme {
                ResultadosDashboardScreen()
            }
        }
    }
}
