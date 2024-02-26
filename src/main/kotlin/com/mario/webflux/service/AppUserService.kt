package com.mario.webflux.service

import com.mario.webflux.model.AppUser
import com.mario.webflux.model.NotFoundException
import com.mario.webflux.repository.AppUserRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class AppUserService(
    private val appUserRepository: AppUserRepository
) {
    fun findAll(): Flux<AppUser> {
        return appUserRepository.findAll()
    }

    fun findById(id: Long): Mono<AppUser> {
        return appUserRepository.findById(id)
            .switchIfEmpty(
                Mono.error(
                    NotFoundException("$id 사용자를 찾을 수 없습니다.")
                )
            )
    }
}