package com.example.schedulertoykotlin.configuration

import org.springframework.boot.autoconfigure.quartz.QuartzProperties
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.quartz.SchedulerFactoryBean
import java.util.*
import javax.sql.DataSource

@Configuration
class SchedulerConfiguration(
    val dataSource: DataSource,
    val applicationContext: ApplicationContext,
    val quartzProperties: QuartzProperties
) {

    @Bean
    fun schedulerFactoryBean(): SchedulerFactoryBean {
        val jobFactory = SchedulerJobFactory()
        jobFactory.setApplicationContext(applicationContext)

        val properties = Properties()
        properties.putAll(quartzProperties.properties)

        val factory = SchedulerFactoryBean()
        factory.setOverwriteExistingJobs(true)
        factory.setDataSource(dataSource)
        factory.setQuartzProperties(properties)
        factory.setJobFactory(jobFactory)
        return factory
    }
}