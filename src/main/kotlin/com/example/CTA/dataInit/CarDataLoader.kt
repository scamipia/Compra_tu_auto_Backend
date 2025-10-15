package com.example.CTA.dataInit

import com.example.CTA.repository.CarRepository
import com.example.CTA.utils.CarBuilder
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order

@Configuration
class CarDataLoader(private val carRepository: CarRepository) {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE + 1) // después de AccountDataLoader
    fun initCars() = CommandLineRunner {

        val car1 = CarBuilder()
            .make("Toyota")
            .model("Corolla")
            .image("toyota_corolla.jpg")
            .fuelType("Nafta")
            .doors(4)
            .transmission("Manual")
            .horsepower(125)
            .build()

        val car2 = CarBuilder()
            .make("Ford")
            .model("Focus")
            .image("ford_focus.jpg")
            .fuelType("Nafta")
            .doors(4)
            .transmission("Automática")
            .horsepower(140)
            .build()

        val car3 = CarBuilder()
            .make("Volkswagen")
            .model("Golf")
            .image("vw_golf.jpg")
            .fuelType("Diesel")
            .doors(3)
            .transmission("Manual")
            .horsepower(150)
            .build()

        carRepository.saveAll(listOf(car1, car2, car3))
    }
}