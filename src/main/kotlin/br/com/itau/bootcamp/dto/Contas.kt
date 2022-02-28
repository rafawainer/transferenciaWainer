package br.com.itau.bootcamp.dto

import java.math.BigDecimal

data class Contas (

    val CPF: String,
    val conta: Int,
    val nome: String,
    var saldo: BigDecimal

)