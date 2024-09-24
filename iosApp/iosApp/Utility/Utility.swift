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
        "kotlin": Color(red: 0.5, green: 0.32, blue: 1.0),
        "java": Color(red: 0.69, green: 0.45, blue: 0.1),
        "python": Color(red: 0.21, green: 0.45, blue: 0.65),
        "javascript": Color(red: 0.95, green: 0.88, blue: 0.35),
        "typescript": Color(red: 0.17, green: 0.45, blue: 0.54),
        "c#": Color(red: 0.09, green: 0.52, blue: 0.0),
        "c++": Color(red: 0.0, green: 0.35, blue: 0.61),
        "go": Color(red: 0.0, green: 0.68, blue: 0.85),
        "rust": Color(red: 0.87, green: 0.65, blue: 0.52),
        "swift": Color(red: 1.0, green: 0.67, blue: 0.27),
        "ruby": Color(red: 0.44, green: 0.08, blue: 0.09),
        "php": Color(red: 0.31, green: 0.36, blue: 0.58),
        "dart": Color(red: 0.0, green: 0.71, blue: 0.67),
        "r": Color(red: 0.1, green: 0.55, blue: 0.91),
        "shell": Color(red: 0.54, green: 0.88, blue: 0.32),
        "perl": Color(red: 0.01, green: 0.6, blue: 0.76),
        "lua": Color(red: 0.0, green: 0.0, blue: 0.5),
        "haskell": Color(red: 0.37, green: 0.32, blue: 0.53),
        "elixir": Color(red: 0.43, green: 0.29, blue: 0.49),
        "clojure": Color(red: 0.86, green: 0.35, blue: 0.33),
        "scala": Color(red: 0.86, green: 0.19, blue: 0.18),
        "objective-c": Color(red: 0.26, green: 0.56, blue: 1.0),
        "c": Color(red: 0.33, green: 0.33, blue: 0.33),
        "html": Color(red: 0.89, green: 0.3, blue: 0.15),
        "css": Color(red: 0.34, green: 0.24, blue: 0.49),
        "scss": Color(red: 0.81, green: 0.39, blue: 0.6),
        "sql": Color(red: 0.0, green: 0.39, blue: 0.64),
        "matlab": Color(red: 0.66, green: 0.72, blue: 0.8),
        "fortran": Color(red: 0.3, green: 0.25, blue: 0.69),
        "pascal": Color(red: 0.89, green: 0.94, blue: 0.44),
        "f#": Color(red: 0.72, green: 0.27, blue: 0.99),
        "assembly": Color(red: 0.43, green: 0.29, blue: 0.07)
    ]
    
    func getLanguageColor(for language: String) -> Color {
        languageColors[language.lowercased()] ?? .blue
    }
}
