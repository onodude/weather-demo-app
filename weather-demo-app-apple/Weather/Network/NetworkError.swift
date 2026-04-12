//
//  NetworkError.swift
//  Weather
//
//  Created by Onur on 4/11/26.
//

enum NetworkError: Error, Equatable {
    case invalidURL
    case invalidResponse
    case httpError(Int)
}
