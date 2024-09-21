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
            case onRetryButtonTapped
        }
        
        @Published private(set) var state: ViewState<[RepositoryItem]> = .loading
        
        private let searchHelper: SearchRepositoriesUseCaseHelper
        private let inputText: String
        private var searchTask: Task<Void, Never>?
        
        init(searchHelper: SearchRepositoriesUseCaseHelper, inputText: String) {
            self.searchHelper = searchHelper
            self.inputText = inputText
        }
        
        func send(_ action: Action) async {
            switch action {
            case .onAppear, .onRetryButtonTapped:
                state = .loading
                await searchRepositories()
            }
        }
        
        private func searchRepositories() async {
            state = .loading
            for await repos in searchHelper.searchRepositories(query: inputText) {
                if repos.isEmpty {
                    print("No repositories found")
                    state = .failed(NSError(domain: "", code: -1, userInfo: [NSLocalizedDescriptionKey: "No repositories found"]))
                } else {
                    print("Repositories loaded: \(repos.count)")
                    state = .loaded(repos)
                }
            }
        }
    }
}
