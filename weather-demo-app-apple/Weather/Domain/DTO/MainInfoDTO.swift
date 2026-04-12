//
//  MainInfoDTO.swift
//  Weather
//
//  Created by Onur on 4/11/26.
//

struct MainInfoDTO: Decodable {
    let temp: Double
    let feelsLike: Double
    let humidity: Int

    enum CodingKeys: String, CodingKey {
        case temp
        case feelsLike = "feels_like"
        case humidity
    }
}
