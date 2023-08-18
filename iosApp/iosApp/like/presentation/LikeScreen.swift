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
        let backgroundColor = Color(hex: viewModel.state.colors.first as! Int64)
        let textColor = Color(hex: viewModel.state.colors.second as! Int64)

        List {
            ForEach(viewModel.state.likedPoems, id: \.self) { poemItem in
                VStack(spacing: 16) {
                    Text(poemItem.verse1)
                        .font(.system(size: 20))
                        
                    Text(poemItem.verse2)
                        .font(.system(size: 20))
                        
                    Text("« \(poemItem.poet) »")
                        .font(.system(size: 16))
                }
                .padding() // Optional: To give some space between cell content and borders
                .background(backgroundColor)
                .listRowBackground(backgroundColor)
                .foregroundColor(textColor)
                .cornerRadius(20)
                .overlay(
                    RoundedRectangle(cornerRadius: 20)
                        .stroke(.white, lineWidth: 1)
                )
                .frame(maxWidth: .infinity)
            }
        }
        .listStyle(PlainListStyle()) // This removes default list styling
        .edgesIgnoringSafeArea(.horizontal) // This extends the content to the screen edges
        .onAppear {
            viewModel.startObserving()
        }
        .onDisappear {
            viewModel.dispose()
        }
    }

}
