package com.mario.webflux.controller

import com.mario.webflux.model.AppUser
import com.mario.webflux.service.AppUserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
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
    fun createUser(@Valid @RequestBody appUserRequest: AppUserRequest): Mono<AppUser> {
        return appUserService.createUser(appUserRequest)
    }

    @PutMapping("/{id}")
    fun updateUser(
        @Valid @RequestBody appUserRequest: AppUserRequest,
        @PathVariable id: Long
    ): Mono<AppUser> {
        return appUserService.updateUser(id, appUserRequest)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable id: Long): Mono<Void> {
        return appUserService.deleteUser(id)
    }
}