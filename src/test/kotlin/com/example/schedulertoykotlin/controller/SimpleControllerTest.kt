package com.example.schedulertoykotlin.controller

import com.example.schedulertoykotlin.job.SimpleJob
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class SimpleControllerTest{

    @Test
    fun className() {
        val className1 = SimpleJob::class.qualifiedName
        val className2 = SimpleJob().javaClass.name
        Assertions.assertThat(className1).isEqualTo(className2)
    }
}