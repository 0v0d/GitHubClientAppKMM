//
//  RepositoryRowView.swift
//  iosApp
//
//  Created by 0v0 on 2024/09/21.
//  Copyright © 2024 orgName. All rights reserved.
//
import SwiftUI
import Shared
import Foundation

struct RepositoryRowView: View {
    let repository: RepositoryItem
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack(spacing: 8) {
                OwnerAvatarView(avatarUrl: repository.owner.avatarUrl)
                RepositoryHeaderView(name: repository.name, ownerLogin: repository.owner.login)
            }
            
            if let description = repository.description_ {
                DescriptionView(text: description)
            }
            
            RepositoryStatsView(stargazersCount: Int(repository.stargazersCount),
                                language: repository.language)
        }
        .padding(.vertical, 8)
    }
}

private struct OwnerAvatarView: View {
    let avatarUrl: String
    
    var body: some View {
        AsyncImage(url: URL(string: avatarUrl)) { image in
            image
                .resizable()
                .aspectRatio(contentMode: .fill)
                .frame(width: 60, height: 60)
                .clipShape(Circle())
                .overlay(Circle().stroke(Color.secondary.opacity(0.3), lineWidth: 1))
        } placeholder: {
            ProgressView()
                .frame(width: 60, height: 60)
        }
    }
}

private struct RepositoryHeaderView: View {
    let name: String
    let ownerLogin: String
    
    var body: some View {
        VStack(alignment: .leading, spacing: 4) {
            Text(name)
                .font(.headline)
            Text(ownerLogin)
                .font(.subheadline)
        }
    }
}

private struct DescriptionView: View {
    let text: String
    
    var body: some View {
        Text(text)
            .font(.caption)
            .foregroundColor(.primary)
            .lineLimit(2)
            .padding(.bottom, 4)
    }
}

private struct RepositoryStatsView: View {
    let stargazersCount: Int
    let language: String?
    
    var body: some View {
        HStack(spacing: 16) {
            HStack(spacing: 4) {
                Image(systemName: "star.fill")
                    .font(.subheadline)
                    .foregroundColor(.secondary)
                Text(getFormattedCount(count:stargazersCount))
                    .font(.caption)
            }
            
            if let language = language {
                LanguageView(language: language)
            }
        }
    }
}

private struct LanguageView: View {
    let language: String
    
    var body: some View {
        HStack(spacing: 4) {
            Image(systemName: "chevron.left.chevron.right")
                .font(.subheadline)
                .foregroundColor(.secondary)
            Text(language)
                .font(.caption)
                .foregroundColor(getLanguageColor(for: language))
        }
    }
}

#Preview {
    RepositoryListView(
        inputText: "test",
        searchHelper: MockSearchRepositoriesUseCaseHelper()
    )
}
