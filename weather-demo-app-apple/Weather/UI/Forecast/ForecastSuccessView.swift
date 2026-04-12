//
//  ForecastSuccessView.swift
//  Weather
//
//  Created by Onur on 4/11/26.
//

import SwiftUI

struct ForecastSuccessView: View {
    let days: [ForecastDay]

    var body: some View {
        ScrollView {
            LazyVStack(alignment: .leading, spacing: 12) {
                ForEach(days) { day in
                    ForecastDayCardView(day: day)
                }
            }
        }
    }
}
