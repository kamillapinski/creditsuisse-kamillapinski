package dev.lapinski.creditsuisse.csbackend

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ApplicationListener
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.event.ContextClosedEvent
import org.testcontainers.containers.PostgreSQLContainer

class PostgresContextInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
    private val container = PostgreSQLContainer<Nothing>("postgres:15")
    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        container.start()

        TestPropertyValues.of(
            "spring.datasource.url=" + container.jdbcUrl,
            "spring.datasource.username=" + container.username,
            "spring.datasource.password=" + container.password,
            "spring.flyway.url=" + container.jdbcUrl,
            "spring.flyway.user=" + container.username,
            "spring.flyway.password=" + container.password
        ).applyTo(applicationContext.environment)

        applicationContext.addApplicationListener(ApplicationListener<ContextClosedEvent> {
            container.stop()
        })
    }
}