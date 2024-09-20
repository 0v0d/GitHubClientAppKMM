//
//  RepositoryListView.swift
//  iosApp
//
//  Created by 0v0 on 2024/09/21.
//  Copyright © 2024 orgName. All rights reserved.
//
import SwiftUI
import Shared

struct RepositoryListView: View {
    let inputText: String
    @ObservedObject private(set) var viewModel: ViewModel
    
    var body: some View {
        VStack {
            ListView(repositories: viewModel.repositories)
                .task { await self.viewModel.startObserving(inputText: inputText) }
        }.navigationTitle("RepositoryList")
    }
}

extension RepositoryListView {
    @MainActor
    class ViewModel: ObservableObject {
        @Published var repositories: [RepositoryItem] = []
        private let searchHelper: SearchRepositoriesUseCaseHelper
        
        init(searchHelper: SearchRepositoriesUseCaseHelper = SearchRepositoriesUseCaseHelper()) {
            self.searchHelper = searchHelper
        }
        
        func startObserving(inputText: String) async {
            for await repoList in searchHelper.searchRepositories(query: inputText) {
                self.repositories = repoList
            }
        }
    }
}

private struct ListView: View {
    let repositories: [RepositoryItem]
    
    var body: some View {
        List(repositories, id: \.id) { repo in
            VStack(alignment: .leading) {
                Text(repo.name)
                    .font(.headline)
                Text(repo.owner.login)
                    .font(.subheadline)
                    .foregroundColor(.secondary)
                
                if let language = repo.language {
                    Text(language)
                        .font(.subheadline)
                        .foregroundColor(.secondary)
                } else {
                    Text("No language information")
                        .font(.subheadline)
                        .foregroundColor(.secondary)
                }
                
                if let description = repo.description_ {
                    Text(description)
                        .font(.subheadline)
                        .foregroundColor(.primary)
                        .lineLimit(2)
                } else {
                    Text("No description")
                        .font(.subheadline)
                        .foregroundColor(.secondary)
                }
            }
        }
    }
}

    
