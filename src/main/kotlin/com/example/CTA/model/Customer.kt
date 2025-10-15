package com.example.CTA.model

import com.example.CTA.utils.OpinionBuilder
import jakarta.persistence.Entity
import java.time.LocalDate

@Entity
class Customer : Account() {

    init {
        role = "CUSTOMER"
    }

    fun giveOpinion(comment: String, rate: Int, car: Car): Opinion {
        return OpinionBuilder()
            .createdDate(LocalDate.now())
            .comment(comment)
            .rate(rate)
            .customer(this)
            .car(car)
            .build()
    }

}