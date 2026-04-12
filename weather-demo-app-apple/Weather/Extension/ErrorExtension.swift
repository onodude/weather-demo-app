//
//  ErrorExtension.swift
//  Weather
//
//  Created by Onur on 4/11/26.
//

import Foundation

extension Error {

    func toRepositoryError() -> RepositoryError {
        if let repoError = self as? RepositoryError {
            return repoError
        }

        if self is URLError {
            return .network
        }

        if let nsError = self as NSError?,
           nsError.domain == NSURLErrorDomain {
            return .network
        }

        return .unknown
    }
}
