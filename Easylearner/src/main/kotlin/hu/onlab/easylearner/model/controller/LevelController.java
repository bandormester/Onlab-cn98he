package hu.onlab.easylearner.model.controller;

import hu.onlab.easylearner.model.entities.Level;
import hu.onlab.easylearner.model.entities.Topic;
import hu.onlab.easylearner.model.service.LevelService;
import hu.onlab.easylearner.model.service.TopicService;
import lombok.AllArgsConstructor;
import lombok.var;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/level")
@AllArgsConstructor
public class LevelController {
    LevelService levelService;

    @GetMapping("")
    ResponseEntity<List<Level>> findAll(){
        List<Level> queryResult = levelService.findAllQueries();
        return ResponseEntity.status(HttpStatus.OK).body(queryResult);
    }


}
