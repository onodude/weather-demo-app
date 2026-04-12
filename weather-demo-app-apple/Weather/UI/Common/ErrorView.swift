//
//  ErrorView.swift
//  Weather
//
//  Created by Onur on 4/12/26.
//

import SwiftUI

struct ErrorView: View {
    let message: String
    let onRetryClicked: () -> Void

    var body: some View {
        VStack(alignment: .leading, spacing: 12) {
            Text(message)
                .font(.body)
                .foregroundStyle(.red)

            Button("Retry", action: onRetryClicked)
                .buttonStyle(.bordered)
        }
    }
}
