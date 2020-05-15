package hu.onlab.easylearner.model.controller;

import hu.onlab.easylearner.model.dto.LearnerProfileDto;
import hu.onlab.easylearner.model.dto.RatingWithNameDto;
import hu.onlab.easylearner.model.entities.Rating;
import hu.onlab.easylearner.model.service.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
@AllArgsConstructor
public class RatingController {
    RatingService ratingService;

    @GetMapping("/{id}")
    ResponseEntity<List<RatingWithNameDto>> getRating(@PathVariable Integer id){
        List<RatingWithNameDto> queryResult = ratingService.getRating(id);
        return ResponseEntity.status(HttpStatus.OK).body(queryResult);
    }

    @GetMapping("/profile/{id}")
    ResponseEntity<LearnerProfileDto> getProfileRating(@PathVariable Integer id){
        LearnerProfileDto queryResult = ratingService.getProfileRating(id);
        return ResponseEntity.status(HttpStatus.OK).body(queryResult);
    }

    @PostMapping("/add")
    ResponseEntity<Void> addRating(@RequestParam Integer lessonId,
                                   @RequestParam Integer ratedId,
                                   @RequestParam String topicName,
                                   @RequestParam Integer comm,
                                   @RequestParam Integer know,
                                   @RequestParam Integer punc,
                                   @RequestParam String text){
        ratingService.addRating(lessonId, ratedId, topicName, comm, know, punc, text);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
