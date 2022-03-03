package br.com.itau.bootcamp.service

import br.com.itau.bootcamp.dto.HistoricoDto
import br.com.itau.bootcamp.exception.NotFoundException
import io.micronaut.context.annotation.Bean
import java.util.stream.Collectors
import javax.inject.Singleton

@Singleton
@Bean
class Historico {

    protected companion object {

        var historico: MutableList<HistoricoDto> = mutableListOf()
    }

    fun salvaHistorico(h: HistoricoDto): Boolean {

        historico.add(h)

        return true
    }

    fun buscaHistoricoPorCPF(cpf: String): List<HistoricoDto>{
        val retorno: List<HistoricoDto>

        if (cpf != null){
            retorno = historico.stream().filter{ t ->
                t.cpfOrigem == cpf
            }.collect(Collectors.toList())
        } else {
            println("Não encontrado o CPF $cpf na busca da lista de transferencias por CPF")
            throw NotFoundException("Não encontrado o CPF $cpf na busca da lista de transferencias por CPF")
        }

        return retorno
    }

    fun buscaHistoricoUltimo(cpf: String): List<HistoricoDto> {

        val retorno: MutableList<HistoricoDto>

        if (cpf != null) {
            retorno = historico.stream().filter{ t ->
                t.cpfOrigem == cpf
            }.collect(Collectors.toList())
        } else {
            println("Não encontrado o CPF $cpf na busca pela ultima transferencia")
            throw NotFoundException("Não encontrado o CPF $cpf na busca pela ultima transferencia")
        }
        return retorno
    }
}