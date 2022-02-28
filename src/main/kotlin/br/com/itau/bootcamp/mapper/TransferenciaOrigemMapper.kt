package br.com.itau.bootcamp.mapper

import br.com.itau.bootcamp.dto.SaqueRequest
import br.com.itau.bootcamp.dto.TransferenciaRequest
import io.micronaut.context.annotation.Bean
import io.micronaut.core.annotation.Introspected

@Introspected
@Bean
open class TransferenciaOrigemMapper: Mapper<TransferenciaRequest, SaqueRequest> {

    override fun map(t: TransferenciaRequest): SaqueRequest {
        return SaqueRequest(
            CPF = t.cpfOrigem,
            conta = t.contaOrigem,
            valorSaque = t.valorTransferencia
        )
    }
}