package hu.onlab.easylearner.model.controller;


import hu.onlab.easylearner.model.dto.LessonWithNamesDto;
import hu.onlab.easylearner.model.entities.Lesson;
import hu.onlab.easylearner.model.service.LessonService;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.ResolutionSyntax;
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

    @GetMapping("/my/booked/{id}")
    ResponseEntity<List<LessonWithNamesDto>> findBookedLessons(@PathVariable Integer id){
        List<LessonWithNamesDto> queryResult = lessonService.findBookedLessons(id);
        return ResponseEntity.status(HttpStatus.OK).body(queryResult);
    }

    @GetMapping("/my/finished/{id}")
    ResponseEntity<List<LessonWithNamesDto>> findFinishedLessons(@PathVariable Integer id){
        List<LessonWithNamesDto> queryResult = lessonService.findFinishedLessons(id);
        return ResponseEntity.status(HttpStatus.OK).body(queryResult);
    }

    @GetMapping("/my/free/teacher/{id}")
    ResponseEntity<List<LessonWithNamesDto>> findFreeLessonsAsTeacher(@PathVariable Integer id){
        List<LessonWithNamesDto> queryResult = lessonService.findFreeLessonsAsTeacher(id);
        return ResponseEntity.status(HttpStatus.OK).body(queryResult);
    }

    @GetMapping("/my/free/student/{id}")
    ResponseEntity<List<LessonWithNamesDto>> findFreeLessonsAsStudent(@PathVariable Integer id){
        List<LessonWithNamesDto> queryResult = lessonService.findFreeLessonsAsStudent(id);
        return ResponseEntity.status(HttpStatus.OK).body(queryResult);
    }

    @GetMapping("/names/student")
    ResponseEntity<List<LessonWithNamesDto>> findLessonsAsStudent(){
        List<LessonWithNamesDto> queryResult = lessonService.findLessonAsStudent();
        return ResponseEntity.status(HttpStatus.OK).body(queryResult);
    }

    @GetMapping("/names/teacher")
    ResponseEntity<List<LessonWithNamesDto>> findLessonsAsTeacher(){
        List<LessonWithNamesDto> queryResult = lessonService.findLessonAsTeacher();
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

    @PostMapping("/add/student")
    void addLessonAsStudent(@RequestParam Integer idOfStudent,
                            @RequestParam String info,
                            @RequestParam Long startTime,
                            @RequestParam Integer paymentValue,
                            @RequestParam Integer idOfLevel,
                            @RequestParam Integer idOfTopic){
        lessonService.addLessonAsStudent(idOfStudent, info,startTime, paymentValue, idOfLevel, idOfTopic);
    }

    @GetMapping("/names")
    ResponseEntity<List<LessonWithNamesDto>> findAllWithNames(){
        List<LessonWithNamesDto> queryResult = lessonService.findAllWithNamesQuery();
        return ResponseEntity.status(HttpStatus.OK).body(queryResult);
    }

    @PutMapping("/book/student")
    ResponseEntity<Void> bookLessonAsStudent(@RequestParam Integer lessonId,
                                             @RequestParam Integer studentId){
        lessonService.bookLessonAsStudent(lessonId, studentId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PutMapping("/book/teacher")
    ResponseEntity<Void> bookLessonAsTeacher(@RequestParam Integer lessonId,
                                             @RequestParam Integer teacherId){
        lessonService.bookLessonAsTeacher(lessonId, teacherId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PutMapping("/cancel/{lessonId}")
    ResponseEntity<Void> cancelLesson(@PathVariable Integer lessonId,
                                        @RequestParam Integer cancellerId){
        lessonService.cancelLesson(lessonId, cancellerId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
