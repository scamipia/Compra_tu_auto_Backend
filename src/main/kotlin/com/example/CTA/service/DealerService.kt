package com.example.CTA.service

import com.example.CTA.repository.CarRepository
import com.example.CTA.repository.DealerRepository
import com.example.CTA.repository.PostRepository
import com.example.CTA.utils.PostBuilder
import com.example.CTA.utils.postPublishDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DealerService {

    @Autowired
    lateinit var postService: PostRepository
    @Autowired
    lateinit var carRepository: CarRepository
    @Autowired
    lateinit var dealerRepository: DealerRepository

    fun publish(dealerId: Long, body: postPublishDTO){
        val car = carRepository.findById(body.carId).orElseThrow()
        val dealer = dealerRepository.findById(dealerId).orElseThrow()
        val post = PostBuilder()
            .price(body.price)
            .description(body.description)
            .car(car)
            .dealer(dealer)
            .build()
        postService.save(post)
    }
}