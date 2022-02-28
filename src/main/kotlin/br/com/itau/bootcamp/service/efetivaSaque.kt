package br.com.itau.bootcamp.service

import br.com.itau.bootcamp.dto.Contas
import java.math.BigDecimal

fun efetivaSaque(conta: Contas, valor: BigDecimal): BigDecimal {
    return conta.saldo - valor
}