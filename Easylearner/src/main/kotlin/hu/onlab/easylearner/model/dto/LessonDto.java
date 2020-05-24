package hu.onlab.easylearner.model.dto;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LessonDto {
    Integer Id;
    Integer TeacherId;
    Integer StudentId;
    String Info;
    Long StartTime;
    Long EndTime;
    String Longitude;
    String Latitude;
    Integer Payment;
    Integer LevelId;
    Integer TopicId;
    Integer OwnerId;
}
