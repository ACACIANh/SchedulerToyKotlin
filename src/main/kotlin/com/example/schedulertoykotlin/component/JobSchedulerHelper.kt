package com.example.schedulertoykotlin.component

import org.quartz.JobDataMap
import org.quartz.JobDetail
import org.springframework.context.ApplicationContext
import org.springframework.scheduling.quartz.JobDetailFactoryBean
import org.springframework.scheduling.quartz.QuartzJobBean
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean
import org.springframework.stereotype.Component
import java.util.*

@Component
class JobSchedulerHelper(
    val jobDetailFactoryBean: JobDetailFactoryBean,
    val simpleTriggerFactoryBean: SimpleTriggerFactoryBean,
) {

    fun createJobDetail(
        jobClass: Class<out QuartzJobBean>, isDurable: Boolean,
        context: ApplicationContext, jobName: String?, jobGroup: String?
    ): JobDetail? {
        val jobDataMap = JobDataMap().also { it.put(jobName + jobGroup, jobClass.name) }

        //default 세팅 후, 필요데이터 추가 입력 -> jobData 초기화 필요
        return jobDetailFactoryBean.also {
            it.setJobClass(jobClass)
            it.setDurability(isDurable)
            it.setApplicationContext(context)
            jobName?.let { it1 -> it.setName(it1) }
            jobGroup?.let { it1 -> it.setGroup(it1) }
            it.setJobDataAsMap(jobDataMap)
            it.afterPropertiesSet()
        }.getObject()
    }

    fun createSimpleTrigger(triggerName: String?, reserveTime: Date, misFireInstruction: Int) =
        simpleTriggerFactoryBean.also {
            triggerName?.let { it1 -> it.setName(it1) }
            it.setStartTime(reserveTime)
            it.setStartDelay(0)
            it.setRepeatInterval(1)
            it.setRepeatCount(0)
            it.setMisfireInstruction(misFireInstruction)
            it.afterPropertiesSet()
        }.getObject()
}