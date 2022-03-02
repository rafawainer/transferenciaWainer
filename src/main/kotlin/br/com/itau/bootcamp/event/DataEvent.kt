package br.com.itau.bootcamp.event

import java.math.BigDecimal
import java.time.LocalDateTime

data class DataEvent (

    var contaOrigem: Int,
    val CPFDestino: String,
    val contaDestino: Int,
    val valorTransferencia: BigDecimal,
    val dataCriacao: LocalDateTime = LocalDateTime.now()

)
