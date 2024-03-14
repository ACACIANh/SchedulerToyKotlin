package com.example.schedulertoykotlin.controller

import org.quartz.Scheduler
import org.quartz.impl.matchers.GroupMatcher
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class JobDetailController(
    val scheduler: Scheduler
) {
    @GetMapping("/jobDetails")
    fun getAllJobDetails(
        @RequestParam groupName: String? //복수로 변경가능
    ): ResponseEntity<Any> {

        val groupMatcher = if (groupName != null) GroupMatcher.groupContains(groupName) else GroupMatcher.anyJobGroup()
        val jobKeys = scheduler.getJobKeys(groupMatcher)
        val jobDetails = jobKeys.map { jobKey ->
            val jobDetail = scheduler.getJobDetail(jobKey)
            jobDetail.key // custom dto 로 변경하면 베스트
        }

        return ResponseEntity.ok(jobDetails)
    }
}