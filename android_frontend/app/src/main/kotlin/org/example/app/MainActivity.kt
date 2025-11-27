package org.example.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.WindowCompat

/**
 * PUBLIC_INTERFACE
 * MainActivity is the single-activity entry point hosting the navigation graph
 * and applying the Ocean Professional theme.
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
        // Navigation is handled by the NavHostFragment in activity_main and toolbars within fragments.
    }
}
