package br.com.itau.bootcamp.dto

import io.micronaut.context.annotation.Bean
import io.micronaut.core.annotation.Introspected
import java.math.BigDecimal
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Introspected
@Bean
data class DepositarRequest (

    @field:NotNull(message = "não pode estar vazio")
    @field:Size(min = 1, max = 11, message = "Numérico com mínimo de 1 e máximo de 11")
    val CPF: String,
    @field:NotNull(message = "não pode estar vazio")
    @field:Size(min = 1, max = 5, message = "Numérico com mínimo de 1 e máximo de 11")
    val conta: Int,
    @field:NotNull(message = "não pode estar vazio")
    val valorDeposito: BigDecimal

)