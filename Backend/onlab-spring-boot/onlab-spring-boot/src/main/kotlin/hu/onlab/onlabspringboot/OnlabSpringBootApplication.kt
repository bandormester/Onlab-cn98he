package hu.onlab.onlabspringboot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OnlabSpringBootApplication

fun main(args: Array<String>) {
	runApplication<OnlabSpringBootApplication>(*args)
}
