package hu.onlab.easylearner.model.controller;

import hu.onlab.easylearner.model.entities.LearnersKT;
import hu.onlab.easylearner.model.service.LearnersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
class LearnersController {

    LearnersService learnersService;

    @GetMapping("")
    ResponseEntity<List<LearnersKT>> findAll() {
        List<LearnersKT> queryResult = learnersService.findAllQueries();
        return ResponseEntity.status(HttpStatus.OK).body(queryResult);
    }

    @GetMapping("/login")
    ResponseEntity<String> tryLogin(@RequestParam String username,
                                    @RequestParam String password){
        Boolean queryResult = learnersService.tryLogin(username, password);
        return queryResult?ResponseEntity.status(HttpStatus.OK).body("Sikeres!"):ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sikertelen!");
    }
}
