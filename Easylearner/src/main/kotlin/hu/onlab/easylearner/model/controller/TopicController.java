package hu.onlab.easylearner.model.controller;

import hu.onlab.easylearner.model.entities.Topic;
import hu.onlab.easylearner.model.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/topic")
@AllArgsConstructor
public class TopicController {
    TopicService topicService;

    @GetMapping("")
    ResponseEntity<List<Topic>> findAll(){
        List<Topic> queryResult = topicService.findAllQueries();
        return ResponseEntity.status(HttpStatus.OK).body(queryResult);
    }

}
