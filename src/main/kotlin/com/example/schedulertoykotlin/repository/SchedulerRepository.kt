package com.example.schedulertoykotlin.repository

import com.example.schedulertoykotlin.entity.SchedulerJobInfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SchedulerRepository : JpaRepository<SchedulerJobInfo, Long> {

    fun findByJobName(jobName: String): SchedulerJobInfo
}