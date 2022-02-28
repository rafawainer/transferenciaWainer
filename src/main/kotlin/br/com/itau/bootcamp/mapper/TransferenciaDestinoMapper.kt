package br.com.itau.bootcamp.mapper

import br.com.itau.bootcamp.dto.DepositarRequest
import br.com.itau.bootcamp.dto.TransferenciaRequest
import io.micronaut.context.annotation.Bean
import io.micronaut.core.annotation.Introspected

@Introspected
@Bean
open class TransferenciaDestinoMapper: Mapper<TransferenciaRequest, DepositarRequest> {

    override fun map(t: TransferenciaRequest): DepositarRequest {
        return DepositarRequest(
            CPF = t.cpfDestino,
            conta = t.contaDestino,
            valorDeposito = t.valorTransferencia
        )
    }
}