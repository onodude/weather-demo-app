//
//  ForecastViewState.swift
//  Weather
//
//  Created by Onur on 4/11/26.
//

enum ForecastViewState: Equatable {
    case idle
    case loading
    case success([ForecastDay])
    case error(String)
}
