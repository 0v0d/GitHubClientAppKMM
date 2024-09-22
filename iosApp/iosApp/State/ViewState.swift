//
//  ViewState.swift
//  iosApp
//
//  Created by 0v0 on 2024/09/21.
//  Copyright © 2024 orgName. All rights reserved.
//

enum SearchState<Value> {
    case loading
    case failed(Error)
    case success(Value)
}
