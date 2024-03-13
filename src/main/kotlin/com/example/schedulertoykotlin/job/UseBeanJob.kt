package com.example.schedulertoykotlin.job

import mu.KotlinLogging
import org.quartz.JobExecutionContext
import org.springframework.scheduling.quartz.QuartzJobBean

private val log = KotlinLogging.logger { }

class UseBeanJob(
    // init something , application context
) : QuartzJobBean() {
    override fun executeInternal(context: JobExecutionContext) {
        log.info { "use bean job" }
    }
}