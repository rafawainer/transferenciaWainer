package br.com.itau.bootcamp.service

import br.com.itau.bootcamp.dto.Contas
import br.com.itau.bootcamp.exception.NotFoundException
import io.micronaut.context.annotation.Bean
import java.math.BigDecimal
import java.net.http.HttpResponse
import java.util.stream.Collectors

@Bean
class ListaConta(var contas: List<Contas>) {

    init {
        val conta1 = Contas(
            CPF = "11111111111",
            conta = 1,
            nome = "Rafael",
            saldo = "50.0".toBigDecimal()
        )
        val conta2 = Contas(
            CPF = "11111111111",
            conta = 2,
            nome = "Rafael",
            saldo = "250.0".toBigDecimal()
        )
        val conta3 = Contas(
            CPF = "2222222222",
            conta = 3,
            nome = "Elyda",
            saldo = "800.0".toBigDecimal()
        )
        val conta4 = Contas(
            CPF = "2222222222",
            conta = 4,
            nome = "Elyda",
            saldo = "10.0".toBigDecimal()
        )
        val conta5 = Contas(
            CPF = "33333333333",
            conta = 5,
            nome = "Guiça",
            saldo = "400.0".toBigDecimal()
        )
        val conta6 = Contas(
            CPF = "44444444",
            conta = 6,
            nome = "Shoyu",
            saldo = "1500.0".toBigDecimal()
        )
        val conta7 = Contas(
            CPF = "88888888888",
            conta = 7,
            nome = "Marina",
            saldo = "4050.0".toBigDecimal()
        )
        val conta8 = Contas(
            CPF = "88888888888",
            conta = 8,
            nome = "Marina",
            saldo = "950.0".toBigDecimal()
        )
        val conta9 = Contas(
            CPF = "12345678901",
            conta = 9,
            nome = "Raul",
            saldo = "0.0".toBigDecimal()
        )
        val conta10 = Contas(
            CPF = "12345678901",
            conta = 10,
            nome = "Raul",
            saldo = "30000.0".toBigDecimal()
        )
        contas = listOf(conta1, conta2, conta3, conta4, conta5, conta6, conta7, conta8, conta9, conta10)
    }

    fun listaContas(cpf: String): List<Contas>{
        return contas.stream().filter{ t ->
            cpf == t.CPF
        }.collect(Collectors.toList())
    }

    fun buscaConta(conta: Int): Contas {

        return contas.stream().filter { t ->
            conta == t.conta
        }.findFirst().orElseThrow{NotFoundException("Conta $conta não encontrada")}
    }

}


