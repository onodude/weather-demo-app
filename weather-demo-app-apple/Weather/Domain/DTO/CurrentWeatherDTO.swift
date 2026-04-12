//
//  CurrentWeatherDTO.swift
//  Weather
//
//  Created by Onur on 4/11/26.
//

struct CurrentWeatherDTO: Decodable {
    let name: String
    let weather: [WeatherInfoDTO]
    let main: MainInfoDTO
    let wind: WindDTO
}
