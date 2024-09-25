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
    @StateObject private var viewModel = InputKeyWordViewModel()
    
    var body: some View {
        NavigationStack {
            VStack {
                SearchTextField(text: $viewModel.text, onSubmit: viewModel.onSubmit)
                    .padding(.horizontal)
                    .padding(.top, 8)
                    .padding(.bottom, 8)
                
                Spacer()
            }
            .navigationTitle("Search")
            .navigationDestination(isPresented: $viewModel.shouldNavigate) {
                RepositoryListView(inputText: viewModel.text, searchHelper: SearchRepositoriesUseCaseHelper())
            }
        }
    }
}

struct InputKeyWordView_MockPreview: View {
    @StateObject private var viewModel = InputKeyWordViewModel()
    
    var body: some View {
        NavigationStack {
            VStack {
                SearchTextField(text: $viewModel.text, onSubmit: viewModel.onSubmit)
                    .padding(.horizontal)
                    .padding(.top, 8)
                    .padding(.bottom, 8)
                
                Spacer()
            }
            .navigationTitle("Search")
            .navigationDestination(isPresented: $viewModel.shouldNavigate) {
                RepositoryListView(inputText: "Mock", searchHelper: MockSearchRepositoriesUseCaseHelper())
            }
        }
    }
}

struct InputKeyWordView_Preview: PreviewProvider {
    static var previews: some View {
        InputKeyWordView_MockPreview()
    }
}
