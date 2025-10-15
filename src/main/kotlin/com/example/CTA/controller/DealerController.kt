package com.example.CTA.controller

import com.example.CTA.service.DealerService
import com.example.CTA.service.JwtService
import com.example.CTA.utils.postPublishDTO
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/dealer")
class DealerController {

    @Autowired
    lateinit var jwtService: JwtService
    @Autowired
    lateinit var dealerService: DealerService

    @PostMapping("/publish")
    fun publish(request: HttpServletRequest, @RequestBody body: postPublishDTO): String{
        val accountId = getAccountIdFromRequest(request)
        dealerService.publish(accountId, body)
        return "Se ha publicado el auto"
    }

    private fun getAccountIdFromRequest(request: HttpServletRequest): Long {
        return jwtService.extractId(request.getHeader("Authorization")).toLong()
    }
}