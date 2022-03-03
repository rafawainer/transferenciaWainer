package br.com.itau.bootcamp.dto

import io.micronaut.context.annotation.Bean
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@Introspected
@Bean
data class TipoConsultaRequest (

//    @field:NotNull(message = "Não pode estar vazio")
//    @field:Size(min = 3, max = 3, message = "Tamanho maximo de 3 caracteres")
//    @Pattern(regexp = "cpf|ult", flags = [Pattern.Flag.CASE_INSENSITIVE])
    val tipoConsulta: String

)
