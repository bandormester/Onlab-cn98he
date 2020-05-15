package hu.onlab.easylearner.model.controller;

import hu.onlab.easylearner.model.entities.LearnersKT;
import hu.onlab.easylearner.model.service.LearnersService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    ResponseEntity<Integer> tryLogin(@RequestParam String username,
                                    @RequestParam String password){
        Integer userId = 0;
        Integer queryResult = learnersService.tryLogin(username, password, userId);
        System.out.println("--------- USER ID = " +queryResult);
        return queryResult!=0?ResponseEntity.status(HttpStatus.OK).body(queryResult):ResponseEntity.status(HttpStatus.NOT_FOUND).body(queryResult);
    }

    @GetMapping(
            value = "/pic/{id}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    ResponseEntity<byte[]> getImage(@PathVariable String id) throws IOException {
        ClassPathResource imgFile = new ClassPathResource("images/"+id+".jpg");
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
    }

    @PostMapping(
            value = "/register"
    )
    void uploadPicture(@RequestBody byte[] pic,
                       @RequestParam String name,
                       @RequestParam String idCardNumber,
                       @RequestParam String username,
                       @RequestParam String password){
        learnersService.addLearner(pic, name, idCardNumber, username, password);
    }
}
