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
