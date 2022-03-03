package br.com.itau.bootcamp.mapper

import br.com.itau.bootcamp.dto.HistoricoDto
import br.com.itau.bootcamp.dto.TransferenciaRequest
import io.micronaut.context.annotation.Bean
import io.micronaut.core.annotation.Introspected

@Introspected
@Bean
open class TransferenciaHistoricoMapper: Mapper<TransferenciaRequest, HistoricoDto> {

    override fun map(t: TransferenciaRequest): HistoricoDto {
        return HistoricoDto(
            cpfOrigem = t.cpfOrigem,
            contaOrigem = t.contaOrigem,
            cpfDestino = t.cpfDestino,
            contaDestino = t.contaDestino,
            valorTransferencia = t.valorTransferencia
        )
    }

}