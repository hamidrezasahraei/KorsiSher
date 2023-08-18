//
//  IOSPoemViewModel.swift
//  iosApp
//
//  Created by Hamidreza Sahraei on 7/2/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension PoemScreen {
    @MainActor class IOSPoemViewModel: ObservableObject {
        private var historyDataSource: PoemHistoryDataSource
        private var poemUseCase: PoemUseCase
        private var likeUseCase: LikeUseCase
        
        private let viewModel: PoemViewModel
        
        @Published var state: PoemState = PoemState(
            poemItem: nil,
            isLoading: false,
            history: [],
            error: nil,
            colors: ColorUtilKt.generateRandomColors()
        )
        
        private var handle: DisposableHandle?
        
        init(historyDataSource: PoemHistoryDataSource, poemUseCase: PoemUseCase, likeUseCase: LikeUseCase) {
            self.historyDataSource = historyDataSource
            self.poemUseCase = poemUseCase
            self.likeUseCase = likeUseCase
            self.viewModel = PoemViewModel(
                poemUseCase: poemUseCase,
                likeUseCase: likeUseCase,
                poemHistoryDataSource: historyDataSource,
                coroutineScope: nil
            )
        }
        
        func onEvent(event: PoemEvent) {
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
