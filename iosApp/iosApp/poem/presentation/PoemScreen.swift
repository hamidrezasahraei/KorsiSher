//
//  PoemScreen.swift
//  iosApp
//
//  Created by Hamidreza Sahraei on 7/2/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct PoemScreen: View {
    
    private var poemHistoryDataSource: PoemHistoryDataSource
    private var poemUseCase: PoemUseCase
    @ObservedObject var viewModel: IOSPoemViewModel
    
    init(poemHistoryDataSource: PoemHistoryDataSource, poemUseCase: PoemUseCase) {
        self.poemHistoryDataSource = poemHistoryDataSource
        self.poemUseCase = poemUseCase
        self.viewModel = IOSPoemViewModel(historyDataSource: poemHistoryDataSource, poemUseCase: poemUseCase)
    }
    
    var body: some View {
        let backgroundColor = Color(hex: viewModel.state.colors.first as! Int64)
        let textColor = Color(hex: viewModel.state.colors.second as! Int64)
        VStack {
            if viewModel.state.poemItem == nil {
                Text("در قدیم در منطقه (طالقان)، مردان دور کرسی جمع می شدند و مشاعره می کردند.. بعد از چند دور که اشعار در ذهن کم می شد، بعضی از خودشان شعر می گفتند! دیگران می گفتند که فلانی کرسی شعر گفت! (در لفظ: برو بابا بازم کرسی شعر گفتی)")
                    .multilineTextAlignment(.trailing)
                    .foregroundColor(textColor)
                    .padding()
                    .font(.system(size: 16))
            } else {
                if let poemItem = viewModel.state.poemItem {
                    VStack(spacing: 16) {
                        Text(poemItem.verse1)
                            .foregroundColor(textColor)
                            .font(.system(size: 20))
                                        
                        Text(poemItem.verse2)
                            .foregroundColor(textColor)
                            .font(.system(size: 20))
                                        
                        Text("« \(poemItem.poet) »")
                            .foregroundColor(textColor)
                            .font(.system(size: 16))
                    }
                }
            }
                        
            ProgressButton(text: "شعری بگو",
                           isLoading: viewModel.state.isLoading,
                           onClick: { viewModel.onEvent(event: PoemEvent.RandomPoem())})
                .foregroundColor(Color.white)
                .padding()
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(backgroundColor)
        .onAppear {
            viewModel.startObserving()
        }
        .onDisappear {
            viewModel.dispose()
        }
    }
}
