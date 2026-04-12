//
//  WeatherInfoDTO.swift
//  Weather
//
//  Created by Onur on 4/11/26.
//

struct WeatherInfoDTO: Decodable {
    let id: Int
    let main: String
    let description: String
    let icon: String
}
