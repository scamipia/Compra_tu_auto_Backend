package com.example.CTA.service

import com.example.CTA.repository.CarRepository
import com.example.CTA.utils.CarDTO
import org.springframework.stereotype.Service

@Service
class CarService(
    private val carRepository: CarRepository
) {

    fun getAllCars(): List<CarDTO> {
        return carRepository.findAll().map { car ->
            CarDTO(
                id = car.id!!,
                make = car.make!!,
                model = car.model!!,
                image = car.image,
                fuelType = car.fuelType,
                doors = car.doors,
                transmission = car.transmission,
                horsepower = car.horsepower,
                year = car.year,
                color = car.color
            )
        }
    }

    fun getCarById(id: Long): CarDTO? {
        return carRepository.findById(id).map { car ->
            CarDTO(
                id = car.id!!,
                make = car.make!!,
                model = car.model!!,
                image = car.image,
                fuelType = car.fuelType,
                doors = car.doors,
                transmission = car.transmission,
                horsepower = car.horsepower,
                year = car.year,
                color = car.color
            )
        }.orElse(null)
    }
}