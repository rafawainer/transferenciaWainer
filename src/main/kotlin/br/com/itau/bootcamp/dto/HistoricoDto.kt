package br.com.itau.bootcamp.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.math.BigDecimal
import java.time.LocalDateTime

data class HistoricoDto (

    val cpfOrigem: String,
    val contaOrigem: Int,
    val cpfDestino: String,
    val contaDestino: Int,
    val valorTransferencia: BigDecimal,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
    val dataTransferencia: LocalDateTime = LocalDateTime.now()

)
