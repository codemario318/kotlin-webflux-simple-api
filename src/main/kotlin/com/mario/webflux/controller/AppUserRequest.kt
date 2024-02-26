package com.mario.webflux.controller

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class AppUserRequest(
    @field:NotBlank(message = "이름을 입력해주세요.")
    val name: String,

    @field:NotBlank(message = "이메일을 입력해주세요.")
    @field:Email(message = "이메일 형식을 확인해주세요.")
    val email: String
)
