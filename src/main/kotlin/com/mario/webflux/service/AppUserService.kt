package com.mario.webflux.service

import com.mario.webflux.controller.AppUserRequest
import com.mario.webflux.model.AppUser
import com.mario.webflux.model.BadRequestException
import com.mario.webflux.model.NotFoundException
import com.mario.webflux.repository.AppUserRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

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

    fun createUser(appUserRequest: AppUserRequest): Mono<AppUser> {
        return findByEmail(appUserRequest.email)
            .switchIfEmpty {
                appUserRepository.save(
                    AppUser(
                        name = appUserRequest.name,
                        email = appUserRequest.email
                    )
                )
            }
    }

    fun updateUser(id: Long, appUserRequest: AppUserRequest): Mono<AppUser> {
        return findById(id)
            .flatMap {
                findByEmail(appUserRequest.email)
                    .switchIfEmpty {
                        appUserRepository.save(
                            AppUser(
                                id = id,
                                name = appUserRequest.name,
                                email = appUserRequest.email,
                            )
                        )
                    }
            }
    }

    private fun findByEmail(email: String): Mono<AppUser> {
        return appUserRepository.findByEmail(email)
            .flatMap {
                Mono.error<AppUser>(
                    BadRequestException("이미 사용중인 이메일입니다.")
                )
            }
    }

    fun deleteUser(id: Long): Mono<Void> {
        return findById(id).flatMap { foundUser ->
            appUserRepository.delete(foundUser)
        }
    }
}