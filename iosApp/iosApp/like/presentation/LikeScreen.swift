//
//  LikeScreen.swift
//  iosApp
//
//  Created by Hamidreza Sahraei on 7/2/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct LikeScreen: View {
    
    private var poemHistoryDataSource: PoemHistoryDataSource
    private var likeUseCase: LikeUseCase

    @ObservedObject var viewModel: IOSLikeViewModel
    
    init(poemHistoryDataSource: PoemHistoryDataSource, likeUseCase: LikeUseCase) {
        self.poemHistoryDataSource = poemHistoryDataSource
        self.likeUseCase = likeUseCase
        self.viewModel = IOSLikeViewModel(historyDataSource: poemHistoryDataSource, likeUseCase: likeUseCase)
    }
    
    
    var body: some View {
        Text(/*@START_MENU_TOKEN@*/"Hello, World!"/*@END_MENU_TOKEN@*/)
    }
}
