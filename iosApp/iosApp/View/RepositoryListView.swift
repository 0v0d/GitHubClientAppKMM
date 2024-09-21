import SwiftUI
import Shared
import Foundation


struct RepositoryListView: View {
    @StateObject private var viewModel: ViewModel
    
    init(inputText: String, searchHelper: SearchRepositoriesUseCaseHelper) {
        _viewModel = StateObject(wrappedValue: ViewModel(searchHelper: searchHelper, inputText: inputText))
    }
    
    var body: some View {
        NavigationStack {
            content
                .navigationTitle("Repositories")
        }
        .task {
            await viewModel.send(.onAppear)
        }
    }
    
    @ViewBuilder
    private var content: some View {
        switch viewModel.state {
        case .loading:
            ProgressView("Loading...")
        case .loaded(let repos):
            repositoryList(repos)
        case .failed(let error):
            ErrorView(error: error, retryAction: { Task { await viewModel.send(.onRetryButtonTapped) } })
        }
    }
    
    private func repositoryList(_ repos: [RepositoryItem]) -> some View {
        List(repos, id: \.id) { repo in
            NavigationLink(value: repo) {
                RepositoryRowView(repository: repo)
            }
        }
        .navigationDestination(for: RepositoryItem.self) { repo in
            RepositoryDetailView(repository: repo)
        }
    }
}

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
                    state = .failed(NSError(domain: "", code: -1, userInfo: [NSLocalizedDescriptionKey: "No repositories found"]))
                } else {
                    state = .loaded(repos)
                }
            }
        }
        
    }
}

struct RepositoryRowView: View {
    let repository: RepositoryItem
    
    var body: some View {
        VStack(alignment: .leading, spacing: 4) {
            Text(repository.name)
                .font(.headline)
            Text(repository.owner.login)
                .font(.subheadline)
                .foregroundColor(.secondary)
            
            if let language = repository.language {
                Text(language)
                    .font(.caption)
                    .foregroundColor(.secondary)
            }
            
            if let description = repository.description_ {
                Text(description)
                    .font(.caption)
                    .foregroundColor(.primary)
                    .lineLimit(2)
            }
        }
        .padding(.vertical, 4)
    }
}

struct RepositoryListView_Previews: PreviewProvider {
    static var previews: some View {
        RepositoryListView(
            inputText: "swift",
            searchHelper: MockSearchRepositoriesUseCaseHelper()
        )
    }
}
