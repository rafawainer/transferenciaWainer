package br.com.itau.bootcamp.dto

import io.micronaut.context.annotation.Bean
import io.micronaut.core.annotation.Introspected
import java.math.BigDecimal
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Introspected
@Bean
data class TransferenciaRequest (

    @field:NotEmpty(message = "Não pode estar vazio")
    @field:Size(min = 2, max = 11, message = "Min de 2 e máx de 11")
    val cpfOrigem: String,
    @field:NotNull(message = "Não pode estar vazio")
    @field:Size(max = 5, message = "Conta com no máximo 5 números")
    val contaOrigem: Int,
    @field:NotEmpty(message = "Não pode estar vazio")
    @field:Size(min = 2, max = 11, message = "Min de 2 e máx de 11")
    val cpfDestino: String,
    @field:NotNull(message = "Não pode estar vazio")
    @field:Size(max = 5, message = "Conta com no máximo 5 números")
    val contaDestino: Int,
    @field:NotNull(message = "Não pode estar vazio")
    val valorTransferencia: BigDecimal

        )
