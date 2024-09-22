//
//  ErrorView.swift
//  iosApp
//
//  Created by 0v0 on 2024/09/21.
//  Copyright © 2024 orgName. All rights reserved.
//
import SwiftUI

struct ErrorView: View {
    let error: Error
    let retryAction: () -> Void
    
    var body: some View {
        VStack {
            Text(error.localizedDescription)
                .font(.headline)
            Button("Retry", action: retryAction)
                .padding()
        }
    }
}
