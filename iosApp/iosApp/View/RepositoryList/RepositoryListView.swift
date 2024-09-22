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
                .navigationDestination(for: RepositoryItem.self) { repo in
                    RepositoryDetailView(repository: repo)
                }
        }
        .task {
            await viewModel.performSearch(.onAppear)
        }
    }
    
    @ViewBuilder
    private var content: some View {
        switch viewModel.state {
        case .loading:
            ProgressView("Loading...")
        case .success(let repos):
            repositoryList(repos)
        case .failed(let error):
            ErrorView(error: error) {
                Task {
                    await viewModel.performSearch(.onRetry)
                }
            }
        }
    }
    
    private func repositoryList(_ repos: [RepositoryItem]) -> some View {
        List(repos, id: \.id) { repo in
            NavigationLink(destination: RepositoryDetailView(repository: repo)) {
                RepositoryRowView(repository: repo)
            }
        }
    }
}

struct RepositoryListView_Previews: PreviewProvider {
    static var previews: some View {
        RepositoryListView(
            inputText: "test",
            searchHelper: MockSearchRepositoriesUseCaseHelper()
        )
    }
}
