package com.example.schedulertoykotlin.job

import mu.KotlinLogging
import org.quartz.JobExecutionContext
import org.springframework.scheduling.quartz.QuartzJobBean

private val log = KotlinLogging.logger { }

class SimpleJob : QuartzJobBean() {
    override fun executeInternal(context: JobExecutionContext) {
        log.info { "simple job" }
    }
}