package br.com.itau.bootcamp.mapper

import br.com.itau.bootcamp.dto.TransferenciaRequest
import br.com.itau.bootcamp.dto.TransferenciaResponse
import io.micronaut.context.annotation.Bean
import io.micronaut.core.annotation.Introspected

@Introspected
@Bean
class TransferenciaMapper: Mapper<TransferenciaRequest, TransferenciaResponse> {

    override fun map(t: TransferenciaRequest): TransferenciaResponse {
        return TransferenciaResponse(
            cpfOrigem = t.cpfOrigem,
            contaOrigem = t.contaOrigem,
            cpfDestino = t.cpfDestino,
            contaDestino = t.contaDestino,
            valorTransferencia = t.valorTransferencia
        )
    }
}