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
    private var likeUseCase: LikeUseCase
    @ObservedObject var viewModel: IOSPoemViewModel
    
    private let appModule = AppModule()
    
    @State private var isBookmarked = false
    
    init(poemHistoryDataSource: PoemHistoryDataSource, poemUseCase: PoemUseCase, likeUseCase: LikeUseCase) {
        self.poemHistoryDataSource = poemHistoryDataSource
        self.poemUseCase = poemUseCase
        self.likeUseCase = likeUseCase
        self.viewModel = IOSPoemViewModel(historyDataSource: poemHistoryDataSource, poemUseCase: poemUseCase, likeUseCase: likeUseCase)
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
                        HStack(spacing: 16) {
                            Image(systemName: "square.and.pencil").foregroundColor(.red)
                            Toggle(isOn: $isBookmarked) {
                                        Image(systemName: isBookmarked ? "heart.fill" : "heart")
                                            .font(.system(size: 18))
                                    }
                                    .tint(.white)
                                    .toggleStyle(.button)
                                    .clipShape(Circle())
                                    .onChange(of: isBookmarked) { value in
                                        viewModel.onEvent(event: PoemEvent.LikePoem(poem: poemItem))
                                    }
                        }
                    }
                    .padding()
                    .cornerRadius(20)
                    .overlay(
                        RoundedRectangle(cornerRadius: 20)
                            .stroke(.white, lineWidth: 1)
                    )
                }
            }
                        
            ProgressButton(text: "یه بیت دیگه بگو 📝",
                           isLoading: viewModel.state.isLoading,
                           onClick: { viewModel.onEvent(event: PoemEvent.RandomPoem())})
                .foregroundColor(Color.white)
                .padding()
            
            NavigationLink(destination: LikeScreen()) {
                Text("قلبی شده‌ها ❤️")
                    .padding(.horizontal)
                    .padding(.vertical, 5)
                    .background(Color(hex: 0xFF444444))
                    .foregroundColor(.white)
                    .cornerRadius(100)
            }
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
