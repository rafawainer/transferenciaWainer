package br.com.itau.bootcamp.dto

import java.time.LocalDateTime

class ErrorView (

    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val erro: String,
    val mensagem: String,
    val path: String

)