package com.example.github.client.kmm.util

import androidx.compose.ui.graphics.Color
import com.example.github.client.kmm.ui.theme.languageColors
import java.util.Locale

fun getColorForLanguage(language: String) = languageColors[language.lowercase()] ?: Color.Gray

fun getFormattedCount(count: Int): String {
    return when {
        count < 1000 -> count.toString()
        count < 1000000 -> "${count / 1000}k"
        else -> String.format(Locale.US, "%.1fm", count / 1000000.0)
    }
}
