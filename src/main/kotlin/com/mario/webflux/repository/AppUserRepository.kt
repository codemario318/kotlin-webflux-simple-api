package com.mario.webflux.repository

import com.mario.webflux.model.AppUser
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface AppUserRepository: ReactiveCrudRepository<AppUser, Long> {
    fun findByEmail(email: String): Mono<AppUser>
}