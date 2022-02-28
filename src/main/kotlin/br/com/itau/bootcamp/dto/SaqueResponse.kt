package br.com.itau.bootcamp.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.math.BigDecimal
import java.time.LocalDateTime

data class SaqueResponse (

    var CPF: String,
    var conta: Int,
    var saldo: BigDecimal,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
    val dataAlteracao: LocalDateTime = LocalDateTime.now()


)