package com.example.CTA.utils

import com.example.CTA.model.Account
import com.example.CTA.model.Car
import com.example.CTA.model.Customer
import com.example.CTA.model.Dealer
import com.example.CTA.model.Opinion
import com.example.CTA.model.Post
import java.time.LocalDate

class CustomerBuilder {

    private val customer = Customer()

    fun id(id: Long) = apply { customer.id = id }
    fun name(name: String) = apply { customer.name = name }
    fun username(username: String) = apply { customer.usernameField = username }
    fun password(password: String) = apply { customer.passwordField = password }
    fun role(role: String) = apply { customer.role = role }

    fun build(): Customer = customer
}

class AccountBuilder {
    private val account = Account()

    fun id(id: Long) = apply { account.id = id }
    fun name(name: String) = apply { account.name = name }
    fun username(username: String) = apply { account.usernameField = username }
    fun password(password: String) = apply { account.passwordField = password }
    fun role(role: String) = apply { account.role = role }

    fun build(): Account = account
}

class DealerBuilder {
    private val dealer = Dealer()

    fun id(id: Long) = apply { dealer.id = id }
    fun name(name: String) = apply { dealer.name = name }
    fun username(username: String) = apply { dealer.usernameField = username }
    fun password(password: String) = apply { dealer.passwordField = password }

    fun build(): Dealer = dealer
}

class CarBuilder {
    private val car = Car()

    fun id(id: Long?) = apply { car.id = id }
    fun make(make: String) = apply { car.make = make }
    fun model(model: String) = apply { car.model = model }
    fun image(image: String?) = apply { car.image = image }
    fun fuelType(fuelType: String?) = apply { car.fuelType = fuelType }
    fun doors(doors: Int?) = apply { car.doors = doors }
    fun transmission(transmission: String?) = apply { car.transmission = transmission }
    fun horsepower(horsepower: Int?) = apply { car.horsepower = horsepower }


    fun build(): Car = car
}

class PostBuilder {
    private val post = Post()

    fun id(id: Long?) = apply { post.id = id }
    fun price(price: Float?) = apply { post.price = price }
    fun createdDate(date: LocalDate?) = apply { post.createdDate = date }
    fun dealer(dealer: Dealer?) = apply { post.dealer = dealer }
    fun car(car: Car?) = apply { post.car = car }
    fun description(desc: String?) = apply { post.description = desc }

    fun build(): Post = post
}

class OpinionBuilder {
    private val opinion = Opinion()

    fun id(id: Long) = apply { opinion.id = id }
    fun comment(comment: String) = apply { opinion.comment = comment }
    fun createdDate(createdDate: LocalDate) = apply { opinion.createdDate = createdDate }
    fun rate(rate: Int) = apply { opinion.rate = rate }
    fun customer(customer: Customer) = apply { opinion.customer = customer }
    fun car(car: Car) = apply { opinion.car = car }

    fun build(): Opinion = opinion
}