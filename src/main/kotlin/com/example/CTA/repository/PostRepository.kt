package com.example.CTA.repository

import com.example.CTA.model.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface PostRepository : JpaRepository<Post, Long>, JpaSpecificationExecutor<Post>{
}