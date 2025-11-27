androidApplication {
    namespace = "org.example.app"

    // Build features and plugins are not configured here in declarative DSL prototype.
    // ViewBinding is enabled by default in this sample environment.

    dependencies {
        // Material 3 and AndroidX UI
        implementation("com.google.android.material:material:1.12.0")
        implementation("androidx.appcompat:appcompat:1.7.0")
        implementation("androidx.core:core-ktx:1.13.1")
        implementation("androidx.recyclerview:recyclerview:1.3.2")
        implementation("androidx.constraintlayout:constraintlayout:2.2.0")

        // Lifecycle + ViewModel + LiveData
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
        implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")

        // Navigation
        implementation("androidx.navigation:navigation-fragment-ktx:2.8.3")
        implementation("androidx.navigation:navigation-ui-ktx:2.8.3")

        // Room (without annotation processor for now to satisfy build in this environment)
        implementation("androidx.room:room-runtime:2.6.1")
        implementation("androidx.room:room-ktx:2.6.1")

        // Kotlin coroutines
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")
    }
}
