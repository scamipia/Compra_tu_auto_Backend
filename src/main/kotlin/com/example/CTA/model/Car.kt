package com.example.CTA.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = null

    @Column(nullable = false)
    var make: String? = null

    @Column(nullable = false)
    var model: String? = null

    @Column
    var image: String? = null

    @Column
    var fuelType: String? = null

    @Column
    var doors: Int? = null

    @Column
    var transmission: String? = null

    @Column
    var horsepower: Int? = null


}