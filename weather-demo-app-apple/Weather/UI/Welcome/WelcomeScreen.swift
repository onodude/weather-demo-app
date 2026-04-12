//
//  WelcomeScreen.swift
//  Weather
//
//  Created by Onur on 4/11/26.
//

import SwiftUI

struct WelcomeScreen: View {
    @ObservedObject var viewModel: WelcomeViewModel
    let onContinue: () -> Void

    var body: some View {
        WelcomeView(
            onContinueClicked: {
                viewModel.continueToApp(onFinished: onContinue)
            }
        )
    }
}
