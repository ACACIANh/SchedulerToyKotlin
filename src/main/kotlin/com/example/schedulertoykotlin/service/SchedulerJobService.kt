package com.example.schedulertoykotlin.service

import com.example.schedulertoykotlin.component.JobSchedulerHelper
import com.example.schedulertoykotlin.entity.SchedulerJobInfo
import com.example.schedulertoykotlin.repository.SchedulerRepository
import mu.KotlinLogging
import org.quartz.SimpleTrigger
import org.springframework.context.ApplicationContext
import org.springframework.scheduling.quartz.QuartzJobBean
import org.springframework.scheduling.quartz.SchedulerFactoryBean
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

private val log = KotlinLogging.logger { }

@Transactional
@Service
class SchedulerJobService(
    val schedulerFactoryBean: SchedulerFactoryBean,
    val schedulerRepository: SchedulerRepository,
    val applicationContext: ApplicationContext,
    val jobSchedulerHelper: JobSchedulerHelper
) {

    fun scheduleNewJob(jobInfo: SchedulerJobInfo, reserveDate: Date) {

        val jobDetail = jobSchedulerHelper.createJobDetail(
            Class.forName(jobInfo.jobClass) as Class<out QuartzJobBean>,
            false,
            applicationContext,
            jobInfo.jobName,
            jobInfo.jobGroup
        )

        val scheduler = schedulerFactoryBean.scheduler

        if (scheduler.checkExists(jobDetail?.key)) {
            log.info { "${jobDetail?.key} : AlreadyExist" }
            return
        }

        val trigger = jobSchedulerHelper.createSimpleTrigger(
            jobInfo.jobName,
            reserveDate,
            SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW
        )

        val scheduledJobDate = scheduler.scheduleJob(jobDetail, trigger)
        jobInfo.jobStatus = "SCHEDULED"
        schedulerRepository.save(jobInfo)
        log.info { "reserve date : $reserveDate" }
        log.info { "reserve date to millis : ${reserveDate.time}" }
        log.info { "reserve scheduleJob.date to millis : $scheduledJobDate" }
        log.info { ">> jobName : [${jobInfo.jobName}]" + " registered and scheduled." }
    }
}