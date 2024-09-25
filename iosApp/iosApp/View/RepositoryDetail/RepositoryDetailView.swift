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
    
    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 16) {
                RepositoryTitleView(name: repository.name)
                OwnerView(owner: repository.owner)
                DescriptionView(description: repository.description_)
                LanguageView(language: repository.language)
                StatisticsView(repository: repository)
            }
            .padding(.horizontal)
            .padding(.vertical, 16)
        }
        .navigationBarTitleDisplayMode(.inline)
    }
}


private struct RepositoryTitleView: View {
    let name: String
    
    var body: some View {
        Text(name)
            .font(.system(size: 28, weight: .bold))
            .foregroundColor(.primary)
            .padding(.bottom, 8)
    }
}

private struct OwnerView: View {
    let owner: OwnerItem
    
    var body: some View {
        HStack(spacing: 8) {
            AvatarImageView(url: owner.avatarUrl)
            Text(owner.login)
                .font(.headline)
                .foregroundColor(.primary)
        }
        .padding(.bottom, 8)
    }
}

private struct AvatarImageView: View {
    let url: String
    
    var body: some View {
        AsyncImage(url: URL(string: url)) { image in
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

private struct DescriptionView: View {
    let description: String?
    
    var body: some View {
        Group {
            if let description = description {
                Text(description)
                    .font(.body)
                    .padding(.vertical, 4)
            }
        }
    }
}

private struct LanguageView: View {
    let language: String?
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            if let language = language {
                HStack(spacing: 8) {
                    Image(systemName: "chevron.left.chevron.right")
                    Text(language)
                        .font(.subheadline)
                        .foregroundColor(getLanguageColor(for: language))
                }
            }
        }
        .padding(.bottom, 8)
    }
}

private struct StatisticsView: View {
    let repository: RepositoryItem
    
    var body: some View {
        HStack {
            StatisticItem(icon: "star.fill", count: Int(repository.stargazersCount), label: "Stars")
            StatisticItem(icon: "eye.fill", count: Int(repository.watchersCount), label: "Watchers")
            StatisticItem(icon: "arrow.triangle.branch", count: Int(repository.forksCount), label: "Forks")
            StatisticItem(icon: "record.circle", count: Int(repository.openIssuesCount), label: "Issues")
        }
    }
}

struct StatisticItem: View {
    let icon: String
    let count: Int
    let label: String
    
    var body: some View {
        VStack {
            Image(systemName: icon)
                .font(.title3)
                .foregroundColor(.secondary)
            
            Text(getFormattedCount(count: count))
                .font(.subheadline)
                .foregroundColor(.primary)
            
            Text(label)
                .font(.caption)
                .foregroundColor(.secondary)
        }
        .frame(maxWidth: .infinity)
        .padding(.horizontal, 8)
    }
}

struct RepositoryDetailView_Previews: PreviewProvider {
    static var previews: some View {
        NavigationView {
            RepositoryDetailView(repository: RepositoryItemMocks().mockRepo1)
        }
    }
}
