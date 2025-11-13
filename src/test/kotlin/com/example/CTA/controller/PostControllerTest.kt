package com.example.CTA.controller

import com.example.CTA.model.Post
import com.example.CTA.service.PostService
import com.example.CTA.utils.CarBuilder
import com.example.CTA.utils.DealerBuilder
import com.example.CTA.utils.PostBuilder
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.http.MediaType;
import java.time.LocalDate
import kotlin.test.Test

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockitoBean
    lateinit var postService: PostService

    @Test
    @WithMockUser
    fun testSearch(){
        // ARRANGE
        val dealer = DealerBuilder()
            .id(1L)
            .name("Concesionario Uno")
            .build()

        val car = CarBuilder()
            .id(1L)
            .make("Toyota")
            .model("Corolla")
            .build()

        val post = PostBuilder()
            .id(1L)
            .price(10000f)
            .createdDate(LocalDate.now())
            .dealer(dealer)
            .car(car)
            .description("Auto en excelente estado")
            .build()

        val pageable = PageRequest.of(0, 10)
        val postPage: Page<Post> = PageImpl(listOf(post), pageable, 1)

        // Mock del service
        `when`(
            postService.searchPosts(
                dealerName = "Concesionario Uno",
                make = "Toyota",
                model = null,
                minPrice = null,
                maxPrice = null,
                pageable = pageable
            )
        ).thenReturn(postPage)

        // ACT & ASSERT
        mockMvc.get("/post/search") {
            param("dealerName", "Concesionario Uno")
            param("make", "Toyota")
            param("page", "0")
            param("size", "10")
            accept(MediaType.APPLICATION_JSON)
        }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.content[0].dealer") { value("Concesionario Uno") }
                jsonPath("$.content[0].make") { value("Toyota") }
                jsonPath("$.content[0].price") { value(10000.0) }
            }
    }

    @Test
    @WithMockUser
    fun testGetPostById() {
        val dealer = DealerBuilder()
            .id(1L)
            .name("Concesionario Uno")
            .build()

        val car = CarBuilder()
            .id(1L)
            .make("Toyota")
            .model("Corolla")
            .image("toyota_corolla.jpg")
            .build()

        val post = PostBuilder()
            .id(1L)
            .price(10000f)
            .dealer(dealer)
            .car(car)
            .description("Auto en excelente estado")
            .build()

        `when`(postService.getPostById(1L)).thenReturn(post)

        mockMvc.get("/post/1") {
            accept(MediaType.APPLICATION_JSON)
        }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.id") { value(1) }
                jsonPath("$.price") { value(10000.0) }
                jsonPath("$.make") { value("Toyota") }
                jsonPath("$.model") { value("Corolla") }
                jsonPath("$.dealerId") { value(1) }
                jsonPath("$.dealer") { value("Concesionario Uno") }
                jsonPath("$.image") { value("toyota_corolla.jpg") }
            }
    }

}