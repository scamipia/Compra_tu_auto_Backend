package com.example.CTA.service

import com.example.CTA.repository.CarRepository
import com.example.CTA.repository.DealerRepository
import com.example.CTA.repository.PostRepository
import com.example.CTA.utils.DealerDTO
import com.example.CTA.utils.PostBuilder
import com.example.CTA.utils.PostResponseDTO
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

    fun getDealer(dealerId: Long): DealerDTO {
        val dealer = dealerRepository.findById(dealerId).orElseThrow()

        val posts = dealer.posts.map { post ->
            PostResponseDTO(
                id = post.id!!,
                price = post.price ?: 0f,
                make = post.car?.make ?: "",
                model = post.car?.model ?: "",
                dealerId = dealerId,
                dealer = dealer.username ?: "Concesionaria ${dealer.id}",
                image = post.car?.image ?: ""
            )
        }

        return DealerDTO(
            id = dealer.id ?: 0,
            name = dealer.username ?: "Concesionaria ${dealer.id}",
            posts = posts
        )
    }


}