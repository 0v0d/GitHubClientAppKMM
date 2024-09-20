package com.example.github.client.kmm.util

import androidx.compose.ui.graphics.Color
import com.example.github.client.kmm.ui.theme.languageColors

class Utility {
    fun getColorForLanguage(language: String) = languageColors[language.lowercase()] ?: Color.Gray
}
