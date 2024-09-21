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
        case .failed:
            failureView
        }
    }
    
    private func repositoryList(_ repos: [RepositoryItem]) -> some View {
        List(repos, id: \.id) { repo in
            NavigationLink(destination: RepositoryDetailView(repository: repo)) {
                RepositoryRowView(repository: repo)
            }
        }
    }
    
    private var failureView: some View {
        VStack {
            Text("Failed to load repositories")
            Button("Retry") {
                Task {
                    await viewModel.send(.onRetryButtonTapped)
                }
            }
            .padding()
        }
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
