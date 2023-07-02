//
//  ProgressButton.swift
//  iosApp
//
//  Created by Hamidreza Sahraei on 7/2/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ProgressButton: View {
    var text: String
    var isLoading: Bool
    var onClick: () -> Void
    var body: some View {
        Button(
            action: {
                if !isLoading {
                    onClick()
                }
            }
        ) {
            if isLoading {
                ProgressView()
                    .animation(.easeInOut, value: isLoading)
                    .padding(5)
                    .background(.black)
                    .cornerRadius(100)
                    .progressViewStyle(CircularProgressViewStyle(tint: .white))
            } else {
                Text("شعری بگو")
                    .animation(.easeInOut, value: isLoading)
                    .padding(.horizontal)
                    .padding(.vertical, 5)
                    .background(.black)
                    .foregroundColor(.white)
                    .cornerRadius(100)
                
            }
            
        }
    }
}

struct ProgressButton_Previews: PreviewProvider {
    static var previews: some View {
        ProgressButton(
            text: "Sher",
            isLoading: false,
            onClick: {}
        )
    }
}
