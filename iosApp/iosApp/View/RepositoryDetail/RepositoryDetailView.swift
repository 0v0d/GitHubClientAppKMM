//
//  RepositoryDetailView.swift
//  iosApp
//
//  Created by 0v0 on 2024/09/21.
//  Copyright © 2024 orgName. All rights reserved.
//
import SwiftUI
import Shared

struct RepositoryDetailView: View {
    let repository: RepositoryItem
    private let languageColors: [String: Color] = [
        "kotlin": .purple,
        "java": .orange,
        "python": .blue,
        "javascript": .yellow,
        "typescript": .cyan,
        "c#": .green,
        "c++": .blue,
        "go": .cyan,
        "rust": .orange,
        "swift": .orange
    ]
    
    var body: some View {
        ScrollView {
            VStack(alignment: .leading) {
                Text(repository.name)
                    .font(.system(size: 28, weight: .bold, design: .rounded))
                    .foregroundColor(.primary)
                    .padding(.bottom, 8)
                ownerView
                descriptionView
                languageView
                statisticsView
                
               Spacer(minLength: 20)
            }
            .padding(.horizontal)
        }
        .navigationBarTitleDisplayMode(.inline)
    }
    
    private var languageView: some View {
        VStack(alignment: .leading) {
            if let language = repository.language {
                HStack(spacing: 6) {
                    Image(systemName: "chevron.left.slash.chevron.right")
                    Text(language)
                        .font(.subheadline)
                        .foregroundColor(languageColor(for: language))
                }
            }
        }.padding()
    }
    
    
    private var ownerView: some View {
        HStack(spacing: 16) {
            AsyncImage(url: URL(string: repository.owner.avatarUrl)) { image in image
                    .resizable()
                    .aspectRatio(contentMode: .fill)
                    .frame(width: 60, height: 60)
                    .clipShape(Circle())
                    .overlay(Circle().stroke(Color.secondary.opacity(0.3), lineWidth: 1))
            } placeholder: {
                ProgressView()
            }
            
            Text(repository.owner.login)
                .font(.headline)
                .foregroundColor(.primary)
        }
    }
    
    @ViewBuilder
    private var descriptionView: some View {
        if let description = repository.description_ {
            Text(description)
                .font(.body)
                .padding(.vertical, 8)
                .padding(.horizontal, 12)
                .background(Color.secondary.opacity(0.1))
                .cornerRadius(8)
        }
    }
    
    private var statisticsView: some View {
        HStack {
            StatisticItem(icon: "star.fill", count: repository.stargazersCount, label: "Stars")
            StatisticItem(icon: "eye.fill", count: repository.watchersCount, label: "Watchers")
            StatisticItem(icon: "tuningfork", count: repository.forksCount, label: "Forks")
            StatisticItem(icon: "exclamationmark.triangle.fill", count: repository.openIssuesCount, label: "Issues")
        }
        .padding(.vertical, 8)
    }
    
    private func languageColor(for language: String) -> Color {
        languageColors[language.lowercased()] ?? .blue
    }
}

struct StatisticItem: View {
    let icon: String
    let count: String
    let label: String
    
    var body: some View {
        VStack {
            Image(systemName: icon)
            Text(count)
                .font(.subheadline)
            Text(label)
                .font(.caption)
        }.padding()
    }
}

#Preview {
    RepositoryDetailView(
        repository: RepositoryItem(
            id: 123456789,
            name:"Hello-World",
            fullName: "octocat/Hello-World",
            owner: OwnerItem(
                login: "octocat",
                avatarUrl: "https://avatars.githubusercontent.com/u/583231?v=4",
                htmlUrl: "https://github.com/octocat"
            ),
            htmlUrl:  "https://github.com/octocat/Hello-World",
            description:"This is your first repository",
            language:  "Kotlin",
            stargazersCount: "1500",
            watchersCount:  "1500",
            forksCount: "300",
            openIssuesCount:  "42"
        )
    )
}
