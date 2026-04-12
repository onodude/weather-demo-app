//
//  WeatherIconView.swift
//  Weather
//
//  Created by Onur on 4/12/26.
//

import SwiftUI

struct IconView: View {
    let iconCode: String
    let size: CGFloat

    init(iconCode: String, size: CGFloat = 72) {
        self.iconCode = iconCode
        self.size = size
    }

    var body: some View {
        let url = URL(string: "\(AppConstants.weatherIconBaseURL)\(iconCode)@4x.png")

        AsyncImage(url: url) { image in
            image
                .renderingMode(.original)
                .resizable()
                .interpolation(.high)
                .scaledToFit()
        } placeholder: {
            Color.clear
        }
        .frame(width: size, height: size)
    }
}
