//
//  WelcomeView.swift
//  Weather
//
//  Created by Onur on 4/11/26.
//

import SwiftUI

struct WelcomeView: View {
    let onContinueClicked: () -> Void

    var body: some View {
        VStack() {
            Text("Welcome")
                .font(.largeTitle)
            
            Spacer().frame(height: 12)

            Text("Search for a city and view the current weather and forecast.")
                .font(.body)
                .multilineTextAlignment(.center)
            
            Spacer().frame(height: 24)

            Button("Get Started", action: onContinueClicked)
                .buttonStyle(.borderedProminent)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .padding(24)
    }
}
