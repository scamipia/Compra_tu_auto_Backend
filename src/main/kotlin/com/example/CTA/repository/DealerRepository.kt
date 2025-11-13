package com.example.CTA.repository

import com.example.CTA.model.Dealer
import org.springframework.data.jpa.repository.JpaRepository

interface DealerRepository : JpaRepository<Dealer, Long> {
}