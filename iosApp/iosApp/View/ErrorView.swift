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
            Text("Failed to load repositories")
                .font(.headline)
            Text(error.localizedDescription)
                .font(.caption)
                .foregroundColor(.red)
                .multilineTextAlignment(.center)
                .padding()
            Button("Retry", action: retryAction)
                .padding()
        }
    }
}
