package hu.onlab.onlabspringboot.test

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class Hello {
    @RequestMapping("/hello")
    fun sayHello(@RequestParam(value = "name") name : String) : String{
        return "Hello $name!"
    }

    @RequestMapping("/bello")
    fun sayBello(@RequestParam(value = "name") name : String) : String{
        return "Hello $name es Kukker!"
    }
}