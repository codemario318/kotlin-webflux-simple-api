package com.mario.webflux.controller

import com.mario.webflux.model.AppUser
import com.mario.webflux.service.AppUserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/user")
class AppUserController(
    private val appUserService: AppUserService
) {
    @GetMapping
    fun getAll(): Flux<AppUser> {
        return appUserService.findAll()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Mono<AppUser> {
        return appUserService.findById(id)
    }

    @PostMapping
    fun createUser(@RequestBody appUserRequest: AppUserRequest): Mono<AppUser> {
        return appUserService.createUser(appUserRequest)
    }
}