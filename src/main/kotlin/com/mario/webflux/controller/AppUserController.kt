package com.mario.webflux.controller

import com.mario.webflux.model.AppUser
import com.mario.webflux.service.AppUserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api")
class AppUserController(
    private val appUserService: AppUserService
) {
    @GetMapping("/user")
    fun getAll(): Flux<AppUser> {
        return appUserService.findAll()
    }

    @GetMapping("/user/{id}")
    fun getById(@PathVariable id: Long): Mono<AppUser> {
        return appUserService.findById(id)
    }
}