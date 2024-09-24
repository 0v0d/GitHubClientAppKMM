//
//  InputKeyWordViewModel.swift
//  iosApp
//
//  Created by 0v0 on 2024/09/23.
//  Copyright © 2024 orgName. All rights reserved.
//
import SwiftUI

class InputKeyWordViewModel: ObservableObject {
    @Published var text: String = ""
    @Published var shouldNavigate: Bool = false
    
    func onSubmit() {
        shouldNavigate = true
    }
}
