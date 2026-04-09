package ni.edu.uam.prototipointerfazusuario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ni.edu.uam.prototipointerfazusuario.ui.theme.PrototipoInterfazUsuarioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrototipoInterfazUsuarioTheme {
                // Se muestra la pantalla de sesión diseñada según los requerimientos de PsycheAI
                SessionScreen()
            }
        }
    }
}
