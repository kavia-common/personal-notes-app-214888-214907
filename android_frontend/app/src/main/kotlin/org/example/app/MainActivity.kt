package org.example.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

/**
 * PUBLIC_INTERFACE
 * MainActivity is the single-activity entry point hosting the navigation graph
 * and applying the Ocean Professional theme. It sets up the top app bar for navigation.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Enable edge-to-edge for modern look
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Optional: follow system dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        // Setup ActionBar with NavController
        val navController = findNavController(R.id.nav_host_fragment)
        // ComponentActivity doesn't have a native ActionBar, but the layout includes a Material toolbar
        // NavigationUp handled via fragment toolbars where necessary.
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
