//
//  StringExtension.swift
//  Weather
//
//  Created by Onur on 4/11/26.
//

import Foundation

extension String {

    func requireCity() throws -> String {
        let trimmed = trimmingCharacters(in: .whitespacesAndNewlines)
        guard !trimmed.isEmpty else {
            throw RepositoryError.emptyCity
        }
        return trimmed
    }

    func normalizedCity() throws -> String {
        return try trimmingCharacters(in: .whitespacesAndNewlines)
            .requireCity()
    }
}

extension Optional where Wrapped == String {
    var orEmpty: String { self ?? "" }
}
