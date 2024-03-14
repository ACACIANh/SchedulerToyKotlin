package com.example.schedulertoykotlin.configuration

import com.example.schedulertoykotlin.job.SimpleJob
import org.springframework.boot.autoconfigure.quartz.QuartzProperties
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.quartz.JobDetailFactoryBean
import org.springframework.scheduling.quartz.SchedulerFactoryBean
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean
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
        factory.setApplicationContext(applicationContext)
        factory.setApplicationContextSchedulerContextKey("applicationContext")
        return factory
    }

    @Bean
    fun jobDetailFactoryBean(): JobDetailFactoryBean {
        // todo: default setting
        return JobDetailFactoryBean()
            .also {
                it.setJobClass(SimpleJob::class.java)
                it.setApplicationContext(applicationContext)
            }
    }

    @Bean
    fun simpleTriggerFactoryBean(): SimpleTriggerFactoryBean {
        // todo: default setting
        return SimpleTriggerFactoryBean()
            .also {
                //do something
            }
    }
}