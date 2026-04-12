//
//  Route.swift
//  Weather
//
//  Created by Onur on 4/11/26.
//

import Foundation

enum Route: Hashable {
    case welcome
    case current
    case forecast(city: String)
}
