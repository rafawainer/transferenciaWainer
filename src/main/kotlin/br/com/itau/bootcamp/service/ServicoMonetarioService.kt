package br.com.itau.bootcamp.service

import br.com.itau.bootcamp.dto.*
import br.com.itau.bootcamp.mapper.TransferenciaDestinoMapper
import br.com.itau.bootcamp.mapper.TransferenciaHistoricoMapper
import br.com.itau.bootcamp.mapper.TransferenciaMapper
import br.com.itau.bootcamp.mapper.TransferenciaOrigemMapper
import jakarta.inject.Singleton

@Singleton
class ServicoMonetarioService (private val dadosContaOrigem: ListaConta,
                               private val origemMapper: TransferenciaOrigemMapper,
                               private val destinoMapper: TransferenciaDestinoMapper,
                               private val transferenciaMapper: TransferenciaMapper,
                               private val transferenciaHistoricoMapper: TransferenciaHistoricoMapper,
                               private val historico: Historico
){

    fun validaDeposito (deposito: DepositarRequest): Contas {
        return dadosContaOrigem.buscaConta(deposito.conta)
    }

    fun realizaDeposito (contas: Contas, deposito: DepositarRequest): DepositarResponse {

        val novoSaldoDeposito = efetivaDeposito(contas, deposito.valorDeposito)

        val retorno = DepositarResponse(
            CPF = contas.CPF,
            conta = contas.conta,
            saldo = novoSaldoDeposito.setScale(2)
        )

        println("Deposito realizado com sucesso: <Conta: ${contas.conta} | Valor Deposito: R$${deposito.valorDeposito} | Novo Saldo: R$$$novoSaldoDeposito")
        return retorno
    }

//    fun realizaDeposito (contas: DepositarRequest): DepositarResponse {
//
//        val encontrarConta = dadosContaOrigem.buscaConta(contas.conta)
//
//        if (encontrarConta.conta > 0 && encontrarConta.CPF == contas.CPF) {
//            encontrarConta.saldo += contas.valorDeposito
//        } else {
////                 throw ExceptionHandler("Conta ${contas.conta} não encontrada")
//            throw Exception("Conta ${contas.conta} não encontrada")
//        }
//        val retorno = DepositarResponse(CPF = contas.CPF, conta = contas.conta, saldo = encontrarConta.saldo.setScale(2))
//        return retorno
//    }

    fun validaSaque(saque: SaqueRequest): Contas {

        val encontrarConta = dadosContaOrigem.buscaConta(saque.conta)

        validaSaldo(encontrarConta, saque.valorSaque)

        return encontrarConta
    }

    fun realizaSaque(conta: Contas, saque: SaqueRequest): SaqueResponse {

        val novoSaldoSaque = efetivaSaque(conta, saque.valorSaque)

        val retorno = SaqueResponse(
            CPF = saque.CPF,
            conta = saque.conta,
            saldo = novoSaldoSaque.setScale(2)
        )

        println("Saque realizado com sucesso: <Conta: ${conta.conta} | Valor saque: R$${saque.valorSaque} | Novo saldo: R$${novoSaldoSaque}")
        return retorno
    }

    fun realizaTransferencia(transferencia: TransferenciaRequest): TransferenciaResponse {

        validaTransferencia(transferencia)

        val contaOrigem = origemMapper.map(transferencia) //TransferenciaRequest dados origem -> SaqueRequest
        val contaDestino = destinoMapper.map(transferencia) //TransferenciaRequest dados destino -> SaqueRequest

        val contaOrigemValidada = validaSaque(contaOrigem)

        realizaSaque(contaOrigemValidada, contaOrigem)
        realizaDeposito(validaDeposito(contaDestino), contaDestino)

        println("Transferencia realizada com sucesso -> <Conta Origem: ${contaOrigem.conta} | Conta Destino: ${contaDestino.conta} | Valor Transferencia: R$${transferencia.valorTransferencia}")

        historico.salvaHistorico(transferenciaHistoricoMapper.map(transferencia))

        println("Transferencia salva na base com sucesso -> <Conta Origem: ${contaOrigem.conta} | Conta Destino: ${contaDestino.conta} | Valor Transferencia: R\$${transferencia.valorTransferencia}\")")

        return transferenciaMapper.map(transferencia)
    }
}
