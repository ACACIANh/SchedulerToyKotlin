package com.example.schedulertoykotlin.service

import mu.KotlinLogging
import org.springframework.stereotype.Service

private val log = KotlinLogging.logger { }

@Service
class CustomService {

    fun service() {
        log.info { "CustomService.service()" }
    }
}