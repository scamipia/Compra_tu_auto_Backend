package com.example.CTA.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany

@Entity
class Dealer : Account() {

    init {
        role = "DEALER"
    }

    @OneToMany(mappedBy = "dealer", cascade = [CascadeType.MERGE])
    val posts: MutableList<Post> = mutableListOf()
}