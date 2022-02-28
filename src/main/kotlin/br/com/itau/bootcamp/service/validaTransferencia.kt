package br.com.itau.bootcamp.service

import br.com.itau.bootcamp.dto.TransferenciaRequest

fun validaTransferencia(t: TransferenciaRequest) {
    if (t.contaOrigem.equals(t.contaDestino)) {
        throw Exception("Contas para Transferencia são identicas <${t.contaOrigem}> | <${t.contaDestino}>")
    }
}