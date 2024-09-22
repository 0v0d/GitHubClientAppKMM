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
                Text(repository.name)
                    .font(.system(size: 28, weight: .bold))
                    .foregroundColor(.primary)
                    .padding(.bottom, 8)
                
                ownerView
                
                descriptionView
                
                languageView
                
                statisticsView
            }
            .padding(.horizontal)
            .padding(.vertical, 16)
        }
        .navigationBarTitleDisplayMode(.inline)
    }
    
    
    private var ownerView: some View {
        HStack(spacing: 8) {
            AsyncImage(url: URL(string: repository.owner.avatarUrl)) { image in
                image
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
        .padding(.bottom, 16)
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
                .padding(.bottom, 16)
        }
    }
    
    private var languageView: some View {
        VStack(alignment: .leading,spacing: 8) {
            if let language = repository.language {
                HStack(spacing: 8) {
                    Image(systemName: "chevron.left.slash.chevron.right")
                    Text(language)
                        .font(.subheadline)
                        .foregroundColor(Utility().getLanguageColor(for: language))
                }
            }
        }
    }
    
    
    private var statisticsView: some View {
        HStack(spacing: 30) {
            StatisticItem(icon: "star.fill", count: repository.stargazersCount, label: "Stars")
            StatisticItem(icon: "eye.fill", count: repository.watchersCount, label: "Watchers")
            StatisticItem(icon: "tuningfork", count: repository.forksCount, label: "Forks")
            StatisticItem(icon: "exclamationmark.triangle.fill", count: repository.openIssuesCount, label: "Issues")
        }
        .padding(.vertical, 8)
    }
}

struct StatisticItem: View {
    let icon: String
    let count: String
    let label: String
    
    var body: some View {
        VStack(alignment: .center, spacing: 4) {
            Image(systemName: icon)
                .font(.title3)
                .foregroundColor(.secondary)
            
            Text(count)
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

#Preview {
    RepositoryDetailView(
        repository: RepositoryItemMocks().mockRepo1
    )
}
