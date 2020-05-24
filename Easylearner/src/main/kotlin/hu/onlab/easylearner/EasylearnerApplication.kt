package hu.onlab.easylearner

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@ComponentScan(basePackages = arrayOf("hu.onlab.easylearner"))
@SpringBootApplication
class EasylearnerApplication

fun main(args: Array<String>) {
	SpringApplication.run(EasylearnerApplication::class.java, *args)
}
