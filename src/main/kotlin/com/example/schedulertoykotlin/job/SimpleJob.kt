package com.example.schedulertoykotlin.job

import mu.KotlinLogging
import org.quartz.JobExecutionContext
import org.springframework.context.ApplicationContext
import org.springframework.scheduling.quartz.QuartzJobBean


private val log = KotlinLogging.logger { }

class SimpleJob : QuartzJobBean() {
    override fun executeInternal(context: JobExecutionContext) {
        val applicationContext: ApplicationContext =
            context.scheduler.context["applicationContext"] as ApplicationContext

        log.info { "simple job" }
        log.info { "applicationContext : $applicationContext" }
    }
}