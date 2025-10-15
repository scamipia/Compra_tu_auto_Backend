package com.example.CTA.repository

import com.example.CTA.model.Car
import com.example.CTA.model.Dealer
import com.example.CTA.model.Post
import jakarta.persistence.criteria.CriteriaBuilder
import org.springframework.data.jpa.domain.Specification

object PostSpecification {

    fun hasDealerName(dealerName: String?): Specification<Post>? {
        return dealerName?.let {
            Specification { root, _, cb ->
                val dealerJoin = root.join<Post, Dealer>("owner")
                cb.like(cb.lower(dealerJoin.get("name")), "%${it.lowercase()}%")
            }
        }
    }

    fun hasCarMake(make: String?): Specification<Post>? {
        return make?.let {
            Specification { root, _, cb ->
                val carJoin = root.join<Post, Car>("car")
                cb.like(cb.lower(carJoin.get("make")), "%${it.lowercase()}%")
            }
        }
    }

    fun hasCarModel(model: String?): Specification<Post>? {
        return model?.let {
            Specification { root, _, cb ->
                val carJoin = root.join<Post, Car>("car")
                cb.like(cb.lower(carJoin.get("model")), "%${it.lowercase()}%")
            }
        }
    }

    fun hasPriceBetween(min: Float?, max: Float?): Specification<Post>? {
        if (min == null && max == null) return null
        return Specification { root, _, cb ->
            val pricePath = root.get<Float>("price")
            when {
                min != null && max != null -> cb.between(pricePath, min, max)
                min != null -> cb.greaterThanOrEqualTo(pricePath, min)
                else -> cb.lessThanOrEqualTo(pricePath, max!!)
            }
        }
    }
}