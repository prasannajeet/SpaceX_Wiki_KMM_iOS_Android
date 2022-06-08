import SwiftUI
import shared
import Combine
private let log = koin.loggerWithTag(tag: "ContentView")
class ObservableMainScreen: ObservableObject {
    private var viewModel: MainCallbackViewModel?
    
    @Published
    var currentString: String = "Something went wrong"
    private var cancellables = [AnyCancellable]()
    
    func activate() {
        let viewModel = KotlinDependencies.shared.getMainCallbackViewModel()
        
        doPublish(viewModel.getCurrentTextState) { [weak self] mainState in
                log.d(message: {"View updating with \(mainState)"})
            self?.currentString = mainState as String
        }.store(in: &cancellables)

        self.viewModel = viewModel
        viewModel.callApi()
    }
    
    func deactivate() {
        cancellables.forEach { $0.cancel() }
        cancellables.removeAll()

        viewModel?.clear()
        viewModel = nil
    }
}

struct ContentView: View {
    @ObservedObject
    var observableModel = ObservableMainScreen()
	var body: some View {
        Text(observableModel.currentString)
            .onAppear(perform: {
                observableModel.activate()
            })
            .onDisappear(perform: {
                observableModel.deactivate()
            })
	}
}
