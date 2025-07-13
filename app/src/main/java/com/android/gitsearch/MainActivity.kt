package com.android.gitsearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.android.gitsearch.presentation.GitSearchApp
import com.android.gitsearch.ui.theme.GitSearchAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GitSearchAndroidTheme {
                GitSearchApp()
            }
        }
    }
}
