package dev.lapinski.creditsuisse.csbackend.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.client.HttpStatusCodeException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class HttpErrorControllerAdvice : ResponseEntityExceptionHandler() {
    @ExceptionHandler(HttpStatusCodeException::class)
    fun handleClientError(ex: HttpStatusCodeException): ResponseEntity<Any> =
        ResponseEntity.status(ex.statusCode).build()
}