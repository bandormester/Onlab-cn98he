package hu.onlab.easylearner.model.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RatingDto {
    Integer Id;
    Integer TeacherId;
    Integer TopicId;
    Integer Punctuality;
    Integer Communication;
    Integer Knowledge;
    String Text;
}
