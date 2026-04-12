//
//  ForecastDTO.swift
//  Weather
//
//  Created by Onur on 4/11/26.
//

struct ForecastDTO: Decodable {
    let list: [ForecastItemDTO]
}
