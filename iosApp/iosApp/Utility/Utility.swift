//
//  Utility.swift
//  iosApp
//
//  Created by 0v0 on 2024/09/22.
//  Copyright © 2024 orgName. All rights reserved.
//
import SwiftUI

class Utility {
   private let languageColors: [String: Color] = [
        "kotlin": .purple,
        "java": .orange,
        "python": .blue,
        "javascript": .yellow,
        "typescript": .cyan,
        "c#": .green,
        "c++": .blue,
        "go": .cyan,
        "rust": .orange,
        "swift": .orange
    ]

    func getLanguageColor(for language: String) -> Color {
        languageColors[language.lowercased()] ?? .blue
    }
}
