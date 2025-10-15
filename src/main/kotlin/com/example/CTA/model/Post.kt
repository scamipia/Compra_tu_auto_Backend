package com.example.CTA.model

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.LocalDate

@Entity
class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = null
    var price: Float?= null
    var createdDate: LocalDate? = LocalDate.now()

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dealer_id")
    var dealer: Dealer? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    var car: Car? = null

    var description: String? = null


}