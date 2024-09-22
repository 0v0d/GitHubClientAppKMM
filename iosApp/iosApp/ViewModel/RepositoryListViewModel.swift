//
//  RepositoryListViewModel.swift
//  iosApp
//
//  Created by 0v0 on 2024/09/21.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared
import Foundation

extension RepositoryListView {
    @MainActor
    final class ViewModel: ObservableObject {
        enum Action {
            case onAppear
            case onRetry
        }
        
        @Published private(set) var state: SearchState<[RepositoryItem]> = .loading
        
        private let searchHelper: SearchRepositoriesUseCaseHelper
        private let inputText: String
        
        init(searchHelper: SearchRepositoriesUseCaseHelper, inputText: String) {
            self.searchHelper = searchHelper
            self.inputText = inputText
        }
        
        func performSearch(_ action: Action) async {
            switch action {
            case .onAppear, .onRetry:
                await searchRepositories()
            }
        }
        
        @MainActor
        private func searchRepositories() async {
            state = .loading
            for await repos in searchHelper.searchRepositories(query: inputText) {
                if repos.isEmpty {
                    state = .failed(NSError(domain: "", code: -1, userInfo: [NSLocalizedDescriptionKey: "No repositories found"]))
                } else {
                    state = .success(repos)
                }
            }
        }
    }
}
