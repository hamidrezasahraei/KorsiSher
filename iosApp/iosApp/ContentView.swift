import SwiftUI
import shared

struct ContentView: View {
    
    private let appModule = AppModule()

	var body: some View {
        PoemScreen(poemHistoryDataSource: appModule.poemHistoryDataSource, poemUseCase: appModule.poemUseCase, likeUseCase: appModule.likeUseCase)
	}
}
