package br.com.itau.bootcamp.exception

import br.com.itau.bootcamp.dto.ErrorView
import io.micronaut.context.annotation.Replaces
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Error
import io.micronaut.runtime.context.env.ConfigurationAdvice
import jakarta.inject.Singleton
import java.net.http.HttpRequest

@ConfigurationAdvice
@Singleton
//@Requires(classes = {NotFoundException::class})
open class ExceptionHandler {

    @Replaces(NotFoundException::class)
    @Error(status = HttpStatus.NOT_FOUND)
    fun handlerNotFound(exception: NotFoundException, request: HttpRequest): ErrorView {

        return ErrorView(
            status = HttpStatus.NOT_FOUND.code,
            erro = HttpStatus.NOT_FOUND.name,
            mensagem = exception.localizedMessage,
            path = request.uri().path
        )

    }

}