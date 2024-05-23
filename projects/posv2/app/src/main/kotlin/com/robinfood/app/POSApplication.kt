package com.robinfood.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = ["com.robinfood.app.*",
        "com.robinfood.repository.*",
        "com.robinfood.network.*",
        "com.robinfood.core.*"]
)
open class POSApplication

fun main() {

    runApplication<POSApplication>()

}