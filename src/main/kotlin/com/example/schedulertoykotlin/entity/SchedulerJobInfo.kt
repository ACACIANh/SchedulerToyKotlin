package com.example.schedulertoykotlin.entity

import javax.persistence.*

@Entity
@Table(name = "scheduler_job_info")
class SchedulerJobInfo(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var jobId: Long? = null,
    var jobName: String? = null,
    var jobGroup: String? = null,
    var jobStatus: String? = null,
    var jobClass: String? = null,
//    var cronExpression: String? = null,
//    var desc: String? = null,
//    var interfaceName: String? = null,
//    var repeatTime: Long? = null,
//    var cronJob: Boolean? = null,
) {

}