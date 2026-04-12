//
//  ForecastItemDTO.swift
//  Weather
//
//  Created by Onur on 4/11/26.
//


struct ForecastItemDTO: Decodable {
    let dateText: String
    let weather: [WeatherInfoDTO]
    let main: MainInfoDTO

    enum CodingKeys: String, CodingKey {
        case dateText = "dt_txt"
        case weather
        case main
    }
}
