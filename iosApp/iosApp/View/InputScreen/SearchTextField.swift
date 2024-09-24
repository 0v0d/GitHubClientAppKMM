//
//  SearchTextField.swift
//  iosApp
//
//  Created by 0v0 on 2024/09/22.
//  Copyright © 2024 orgName. All rights reserved.
//
import SwiftUI

struct SearchTextField: View {
    @Binding var text: String
    var onSubmit: () -> Void
    
    var body: some View {
        HStack {
            Image(systemName: "magnifyingglass")
                .foregroundColor(Color.gray)
                .font(.system(size: 20, weight: .medium))
            
            TextField("リポジトリを検索", text: $text)
                .font(.system(size: 17))
                .foregroundColor(Color.primary)
                .onSubmit(onSubmit)
            
            if !text.isEmpty {
                Button(action: {
                    text = ""
                }) {
                    Image(systemName: "xmark.circle.fill")
                        .foregroundColor(Color.secondary)
                        .font(.system(size: 16, weight: .medium))
                }
            }
        }
        .padding(EdgeInsets(top: 15, leading: 20, bottom: 15, trailing: 20))
        .background(Color(.systemGray6))
        .cornerRadius(15)
        .shadow(color: Color.black.opacity(0.1), radius: 5, x: 0, y: 2)
    }
}
