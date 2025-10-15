package com.example.CTA.service

import com.example.CTA.model.Post
import com.example.CTA.repository.PostRepository
import com.example.CTA.repository.PostSpecification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service

@Service
class PostService(
    private val postRepository: PostRepository
) {
    fun searchPosts(
        dealerName: String?,
        make: String?,
        model: String?,
        minPrice: Float?,
        maxPrice: Float?,
        pageable: Pageable
    ): Page<Post> {
        var spec: Specification<Post>? = null

        spec = spec.andIfNotNull(PostSpecification.hasDealerName(dealerName))
        spec = spec.andIfNotNull(PostSpecification.hasCarMake(make))
        spec = spec.andIfNotNull(PostSpecification.hasCarModel(model))
        spec = spec.andIfNotNull(PostSpecification.hasPriceBetween(minPrice, maxPrice))

        return postRepository.findAll(spec, pageable)
    }

    private fun <T> Specification<T>?.andIfNotNull(other: Specification<T>?): Specification<T>? {
        return if (other != null) {
            this?.and(other) ?: other
        } else this
    }
}
