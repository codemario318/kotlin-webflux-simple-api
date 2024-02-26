package com.mario.webflux.repository

import com.mario.webflux.model.AppUser
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface AppUserRepository: ReactiveCrudRepository<AppUser, Long>