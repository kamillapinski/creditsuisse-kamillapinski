package dev.lapinski.creditsuisse.csbackend

import org.springframework.stereotype.Component
import java.time.LocalDateTime

/**
 * Use this component wherever you need to retrieve current date/time.
 */
@Component
class AppClock {
    fun localDateTimeNow() = LocalDateTime.now()
}