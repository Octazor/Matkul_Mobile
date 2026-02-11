package com.example.helloui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // State untuk menyimpan apakah mode gelap aktif atau tidak
            var isDarkMode by remember { mutableStateOf(false) }

            // Mengatur Tema berdasarkan state isDarkMode
            MaterialTheme(
                colorScheme = if (isDarkMode) darkColorScheme() else lightColorScheme()
            ) {
                // Surface adalah background utama aplikasi
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(
                        isDarkMode = isDarkMode,
                        onThemeChanged = { isDarkMode = it }
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(isDarkMode: Boolean, onThemeChanged: (Boolean) -> Unit) {
    // State untuk input nama
    var nameInput by remember { mutableStateOf("") }
    // State untuk teks yang akan ditampilkan setelah tombol ditekan
    var greetingText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp), // Memberi jarak dari pinggir layar
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 1. TextInput (Nama)
        OutlinedTextField(
            value = nameInput,
            onValueChange = { nameInput = it },
            label = { Text("Masukkan Nama") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 2. Tombol "Sapa"
        Button(
            onClick = {
                // Logika: Saat tombol ditekan, update greetingText
                greetingText = "Hello $nameInput!"
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sapa")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 3. TextView / Text Hasil
        if (greetingText.isNotEmpty()) {
            Text(
                text = greetingText,
                fontSize = 24.sp,
                style = MaterialTheme.typography.headlineMedium
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        // 4. Switch Toggle "Mode Gelap/Terang"
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = if (isDarkMode) "Mode Gelap" else "Mode Terang")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = isDarkMode,
                onCheckedChange = { onThemeChanged(it) }
            )
        }
    }
}