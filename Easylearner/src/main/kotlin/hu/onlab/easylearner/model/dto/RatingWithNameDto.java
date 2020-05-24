package hu.onlab.easylearner.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RatingWithNameDto {
    Integer Id;
    Integer TeacherId;
    String TopicName;
    Integer Punctuality;
    Integer Communication;
    Integer Knowledge;
    String Text;
}
