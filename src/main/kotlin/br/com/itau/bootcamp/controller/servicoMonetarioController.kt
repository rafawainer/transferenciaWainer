package br.com.itau.bootcamp.controller

import br.com.itau.bootcamp.config.DynamoDBMapperConfig
import br.com.itau.bootcamp.dto.*
import br.com.itau.bootcamp.service.ListaConta
import br.com.itau.bootcamp.service.ServicoMonetarioService
import io.micronaut.context.annotation.Parameter
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.validation.Validated
import java.time.LocalDateTime
import javax.validation.Valid

@Controller()
@Validated
open class ServicoMonetarioController(private val servicoMonetario: ServicoMonetarioService,
                                      private val dadosConta: ListaConta
) {
    init {
        DynamoDBMapperConfig()
    }

    @Get("/contas/{cpf}")
    fun consultarPorCPF(@PathVariable cpf: String): List<Contas>{
        println("\nAplicacao Transferencias [Contas] inicializada CPF: $cpf-> Post [[${LocalDateTime.now()}]]")
        return dadosConta.listaContas(cpf)
    }

    @Post("/deposito")
    fun depositar(@Body @Valid deposito: DepositarRequest): io.micronaut.http.HttpResponse<DepositarResponse> {
        println("\nAplicacao Transferencias [Deposito] inicializada -> Post [[${LocalDateTime.now()}]]")
        val dadosConta = servicoMonetario.validaDeposito(deposito)
        return io.micronaut.http.HttpResponse.created(servicoMonetario.realizaDeposito(dadosConta, deposito))
    }

    @Post("/saque")
    fun sacar(@Body @Valid saque: SaqueRequest): io.micronaut.http.HttpResponse<SaqueResponse> {
        println("\nAplicacao Transferencias [Saque] inicializada -> Post [[${LocalDateTime.now()}]]")

        val dadosConta = servicoMonetario.validaSaque(saque)
        return io.micronaut.http.HttpResponse.created(servicoMonetario.realizaSaque(dadosConta, saque))
    }

    @Post("/transferencias")
    fun transferencia(@Body @Valid transferencias: TransferenciaRequest): HttpResponse<TransferenciaResponse>{
        println("\nAplicacao Transferencias [Transferencias] inicializada -> Post [[${LocalDateTime.now()}]]")

        return HttpResponse.created(servicoMonetario.realizaTransferencia(transferencia = transferencias))
    }

}