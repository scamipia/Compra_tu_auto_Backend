package com.example.CTA.controller

import com.example.CTA.model.Post
import com.example.CTA.service.PostService
import com.example.CTA.utils.PostResponseDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class PostController {

    @Autowired
    lateinit var postService: PostService

    @GetMapping("/search")
    fun searchPosts(
        @RequestParam(required = false) dealerName: String?,
        @RequestParam(required = false) make: String?,
        @RequestParam(required = false) model: String?,
        @RequestParam(required = false) minPrice: Float?,
        @RequestParam(required = false) maxPrice: Float?,
        pageable: Pageable
    ): Page<PostResponseDTO> {
        return postService.searchPosts(dealerName, make, model, minPrice, maxPrice, pageable)
            .map { PostResponseDTO(
                it.id!!,
                it.price!!,
                it.car!!.make!!,
                it.car!!.model.toString(),
                it.dealer!!.id!!,
                it.dealer!!.name.toString(),
                it.car!!.image.toString()
            )
            }
    }

    @GetMapping("/post/{id}")
    fun getPost(@PathVariable id: Long): PostResponseDTO {
        val post = postService.getPostById(id)
        return postToDTO(post)
    }

    private fun postToDTO(post: Post) = PostResponseDTO(
        id = post.id!!,
        price = post.price!!,
        make = post.car!!.make!!,
        model = post.car!!.model!!,
        dealerId = post.dealer!!.id!!,
        dealer = post.dealer!!.name!!,
        image = post.car!!.image ?: ""
    )
}