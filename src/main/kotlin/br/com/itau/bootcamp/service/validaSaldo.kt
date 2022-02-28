package br.com.itau.bootcamp.service

import br.com.itau.bootcamp.dto.Contas
import java.math.BigDecimal

fun validaSaldo(conta: Contas, valor: BigDecimal): Boolean {

    return (conta.saldo) >= valor
}