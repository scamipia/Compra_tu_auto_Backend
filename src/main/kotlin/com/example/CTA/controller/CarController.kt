package com.example.CTA.controller

import com.example.CTA.service.CarService
import com.example.CTA.utils.CarDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/car")
@CrossOrigin(origins = ["*"])
class CarController(
    private val carService: CarService
) {

    @GetMapping("/all")
    fun getAllCars(): ResponseEntity<List<CarDTO>> {
        val cars = carService.getAllCars()
        return ResponseEntity.ok(cars)
    }

    @GetMapping("/{id}")
    fun getCarById(@PathVariable id: Long): ResponseEntity<CarDTO> {
        val car = carService.getCarById(id)
        return if (car != null) ResponseEntity.ok(car)
        else ResponseEntity.notFound().build()
    }
}