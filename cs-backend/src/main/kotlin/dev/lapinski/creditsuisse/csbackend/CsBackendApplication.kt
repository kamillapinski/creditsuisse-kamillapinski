package dev.lapinski.creditsuisse.csbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CsBackendApplication

fun main(args: Array<String>) {
    runApplication<CsBackendApplication>(*args)
}
