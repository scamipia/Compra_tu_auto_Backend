package com.example.CTA.model

import com.example.CTA.utils.CarBuilder
import com.example.CTA.utils.CustomerBuilder
import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals

class CustomerTest {

    @Test
    fun `Customer give opinion on a car`(){
        val car = CarBuilder().id(1).build()
        val customer = CustomerBuilder().id(1).build()

        val opinion: Opinion = customer.giveOpinion("comment", 5, car)

        assertEquals(opinion.car!!.id, car.id, "Car id should match with car id on the opinion")
        assertEquals(opinion.customer!!.id, customer.id, "Customer id should match with customer id on the opinion")
        assertEquals(opinion.createdDate, LocalDate.now(), "Opinion created date should be equal to current day")
    }

}