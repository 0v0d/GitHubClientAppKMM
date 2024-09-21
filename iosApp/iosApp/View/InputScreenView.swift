//
//  InputScreenView.swift
//  iosApp
//
//  Created by 0v0 on 2024/09/21.
//  Copyright © 2024 orgName. All rights reserved.
//
import SwiftUI
import Shared

struct InputScreenView: View {
    @State private var text: String = ""
    @State private var shouldNavigate: Bool = false
    
    var body: some View {
        NavigationStack {
            VStack {
                HStack{
                    Image(systemName: "magnifyingglass")
                        .foregroundColor(.gray)
                        .font(.system(size: 20, weight: .medium))
                    
                    TextField("検索", text: $text)
                        .font(.system(size: 17))
                        .onSubmit {
                            shouldNavigate = true
                        }
                    
                    if !text.isEmpty {
                        Button(action: {
                            text = ""
                        }) {
                            Image(systemName: "xmark.circle.fill")
                                .foregroundColor(.gray)
                                .font(.system(size: 16, weight: .medium))
                        }
                    }
                }
                .padding(EdgeInsets(top: 15, leading: 20, bottom: 15, trailing: 20))
                .background(Color(.systemBackground))
                .cornerRadius(15)
                .shadow(color: Color.black.opacity(0.1), radius: 5, x: 0, y: 2)
                .padding(.horizontal)
                .padding(.top, 8)
                .padding(.bottom, 8)
                
                Spacer()
            }            .navigationTitle("検索")
            .navigationDestination(isPresented: $shouldNavigate) {
                RepositoryListView(inputText: text,searchHelper: SearchRepositoriesUseCaseHelper())
            }
        }
    }
}
