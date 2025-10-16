package com.example.CTA.dataInit

import com.example.CTA.model.Post
import com.example.CTA.repository.CarRepository
import com.example.CTA.repository.DealerRepository
import com.example.CTA.repository.PostRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order

@Configuration
class PostDataLoader(
    private val postRepository: PostRepository,
    private val carRepository: CarRepository,
    private val dealerRepository: DealerRepository
) {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE + 2) // se ejecuta después de CarDataLoader
    fun initPosts() = CommandLineRunner {

        val cars = carRepository.findAll()
        val dealers = dealerRepository.findAll()

        if (cars.isEmpty() || dealers.isEmpty()) {
            println("⚠️ No se cargaron posts: faltan autos o concesionarias.")
            return@CommandLineRunner
        }

        val toyota = cars.firstOrNull { it.make == "Toyota" }
        val ford = cars.firstOrNull { it.make == "Ford" }
        val volkswagen = cars.firstOrNull { it.make == "Volkswagen" }

        val dealer1 = dealers.getOrNull(0)
        val dealer2 = dealers.getOrNull(1)
        val dealer3 = dealers.getOrNull(2)

        val post1 = Post().apply {
            price = 35000000f
            description = "Toyota Corolla 2020 — confiable, cómodo y eficiente. Ideal para ciudad y ruta."
            car = toyota
            dealer = dealer1
        }

        val post2 = Post().apply {
            price = 42000000f
            description = "Ford Focus Titanium 2021 con caja automática, 4 puertas, interior premium y bajo consumo."
            car = ford
            dealer = dealer1
        }

        val post3 = Post().apply {
            price = 48000000f
            description = "Volkswagen Golf TDI con motor diésel y 150 CV. Potente y con excelente rendimiento."
            car = volkswagen
            dealer = dealer1
        }

        postRepository.saveAll(listOf(post1, post2, post3))

        println("✅ Se cargaron ${postRepository.count()} publicaciones iniciales.")
    }
}
