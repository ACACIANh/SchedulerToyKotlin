package com.example.schedulertoykotlin.job

import com.example.schedulertoykotlin.service.CustomService
import mu.KotlinLogging
import org.quartz.JobExecutionContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.scheduling.quartz.QuartzJobBean
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger { }

@Component
class SimpleJob() : QuartzJobBean() {

    @Autowired
    private lateinit var customService: CustomService

    override fun executeInternal(context: JobExecutionContext) {
        val applicationContext: ApplicationContext =
            context.scheduler.context["applicationContext"] as ApplicationContext

        log.info { "simple job" }
        log.info { "applicationContext : $applicationContext" }
        log.info { "customService : $customService" }

        customService.service()
    }
}