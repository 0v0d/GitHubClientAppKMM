//
//  InputScreenView.swift
//  iosApp
//
//  Created by 0v0 on 2024/09/21.
//  Copyright © 2024 orgName. All rights reserved.
//
import SwiftUI
import Shared

struct InputKeyWordView: View {
    @State private var text: String = ""
    @State private var shouldNavigate: Bool = false
    
    var body: some View {
        NavigationStack {
            VStack {
                SearchTextField(text: $text, onSubmit: {
                    shouldNavigate = true
                })
                .padding(.horizontal)
                .padding(.top, 8)
                .padding(.bottom, 8)
                
                Spacer()
            }
            .navigationTitle("検索")
            .navigationDestination(isPresented: $shouldNavigate) {
                RepositoryListView(inputText: text, searchHelper: SearchRepositoriesUseCaseHelper())
            }
        }
    }
}


struct InputKeyWordView_Preview: View {
    @State private var text = ""
    @State private var shouldNavigate: Bool = false
    var body: some View {
        NavigationStack {
            VStack {
                SearchTextField(text: $text, onSubmit: {
                    shouldNavigate = true
                })
                .padding(.horizontal)
                .padding(.top, 8)
                .padding(.bottom, 8)
                
                Spacer()
            }
            .navigationTitle("検索")
            .navigationDestination(isPresented:  $shouldNavigate) {
                RepositoryListView(inputText: "Mock", searchHelper: MockSearchRepositoriesUseCaseHelper())
            }
        }
    }
}

#Preview {
    InputKeyWordView_Preview()
}
