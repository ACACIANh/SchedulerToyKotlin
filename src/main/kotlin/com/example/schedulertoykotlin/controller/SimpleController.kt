package com.example.schedulertoykotlin.controller

import com.example.schedulertoykotlin.dto.SimpleJobRequest
import com.example.schedulertoykotlin.entity.SchedulerJobInfo
import com.example.schedulertoykotlin.job.SimpleJob
import com.example.schedulertoykotlin.service.SchedulerJobService
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.ZoneId
import java.util.*

private val log = KotlinLogging.logger { }

@RestController
class SimpleController(
    val schedulerJobService: SchedulerJobService
) {

    @PostMapping("/simple")
    fun simple(
        @RequestBody request: SimpleJobRequest
    ): ResponseEntity<Any> {

        val schedulerJobInfo = SchedulerJobInfo().also {
            it.jobClass = SimpleJob::class.qualifiedName
            it.jobName = request.name
            it.jobGroup = request.group
        }

        val instantTime = request.localDateTime.atZone(ZoneId.systemDefault()).toInstant()
        val date = Date.from(instantTime)
        log.info { "currentTimeMillis : ${System.currentTimeMillis()}" }
        log.info { "current date : ${Date()}" }
        log.info { "reserve date : $date" }
        schedulerJobService.scheduleNewJob(schedulerJobInfo, date)

        return ResponseEntity.ok("${request.localDateTime}")
    }
}