//
//  InputKeyWordViewModel.swift
//  iosApp
//
//  Created by 0v0 on 2024/09/23.
//  Copyright © 2024 orgName. All rights reserved.
//
import SwiftUI

//TODO: Observationに変更
//https://qiita.com/lovee/items/4909d0166bba973b3bc0
class InputKeyWordViewModel: ObservableObject {
    @Published var text: String = ""
    @Published var shouldNavigate: Bool = false
    
    func onSubmit() {
        shouldNavigate = true
    }
}
