package hu.onlab.easylearner.model.controller;


import hu.onlab.easylearner.model.dto.LessonWithNamesDto;
import hu.onlab.easylearner.model.entities.Lesson;
import hu.onlab.easylearner.model.service.LessonService;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lesson")
@AllArgsConstructor
public class LessonController {

    LessonService lessonService;

    @GetMapping("")
    ResponseEntity<List<Lesson>> findAll(){
        List<Lesson> queryResult = lessonService.findAllQueries();
        return ResponseEntity.status(HttpStatus.OK).body(queryResult);
    }

    @PostMapping("/add/teacher")
    void addLessonAsTeacher(@RequestParam Integer idOfTeacher,
                            @RequestParam String info,
                            @RequestParam Long startTime,
                            @RequestParam Integer paymentValue,
                            @RequestParam Integer idOfLevel,
                            @RequestParam Integer idOfTopic){
        lessonService.addLessonAsTeacher(idOfTeacher, info,startTime, paymentValue, idOfLevel, idOfTopic);
    }

    @GetMapping("/names")
    ResponseEntity<List<LessonWithNamesDto>> findAllWithNames(){
        List<LessonWithNamesDto> queryResult = lessonService.findAllWithNamesQuery();
        return ResponseEntity.status(HttpStatus.OK).body(queryResult);
    }

    @PutMapping("/book/student")
    ResponseEntity<Void> bookLessonAsStudent(@RequestParam Integer lessonId,
                                             @RequestParam Integer studentId){
        lessonService.bookLesson(lessonId, studentId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
