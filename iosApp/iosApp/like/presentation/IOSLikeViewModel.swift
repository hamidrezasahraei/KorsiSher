//
//  IOSLikeViewModel.swift
//  iosApp
//
//  Created by Hamidreza Sahraei on 7/2/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension LikeScreen {
    @MainActor class IOSLikeViewModel: ObservableObject {
        private var historyDataSource: PoemHistoryDataSource
        private var likeUseCase: LikeUseCase
        
        private let viewModel: LikeViewModel
        
        @Published var state: LikeState = LikeState(
            isLoading: false,
            likedPoems: [],
            error: nil,
            colors: ColorUtilKt.generateRandomColors()
        )
        
        private var handle: DisposableHandle?
        
        init(historyDataSource: PoemHistoryDataSource, likeUseCase: LikeUseCase) {
            self.historyDataSource = historyDataSource
            self.likeUseCase = likeUseCase
            self.viewModel = LikeViewModel(
                likeUseCase: likeUseCase,
                poemHistoryDataSource: historyDataSource,
                coroutineScope: nil
            )
        }
        
        func onEvent(event: LikeEvent) {
            viewModel.onEvent(event: event)
        }
        
        func startObserving() {
            handle = viewModel.state.subscribe(onCollect: { state in
                if let state = state {
                    self.state = state
                }
            })
        }
        
        func dispose() {
            handle?.dispose()
        }
    }
}
