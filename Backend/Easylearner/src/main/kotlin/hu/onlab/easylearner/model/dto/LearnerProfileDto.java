package hu.onlab.easylearner.model.dto;

import hu.onlab.easylearner.model.entities.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LearnerProfileDto {
    String picUrl;
    String fullName;
    String userName;
    Double punctuality;
    Double knowledge;
    Double communication;
    List<Rating> ratings;
}
