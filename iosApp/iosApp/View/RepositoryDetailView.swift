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
        List {
            Section(header: Text("Details")) {
                LabeledContent("Name", value: repository.name)
                LabeledContent("Owner", value: repository.owner.login)
                if let language = repository.language {
                    LabeledContent("Language", value: language)
                }
            }
            
            if let description = repository.description_ {
                Section(header: Text("Description")) {
                    Text(description)
                }
            }
        }
        .navigationTitle(repository.name)
    }
}

