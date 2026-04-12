//
//  RepositoryError.swift
//  Weather
//
//  Created by Onur on 4/11/26.
//

enum RepositoryError: Error, Equatable {
    case emptyCity
    case network
    case unknown
}

extension RepositoryError {
    var message: String {
        switch self {
        case .emptyCity:
            return "Please enter a city"
        case .network:
            return "Network error. Please try again."
        case .unknown:
            return "Something went wrong"
        }
    }
}
