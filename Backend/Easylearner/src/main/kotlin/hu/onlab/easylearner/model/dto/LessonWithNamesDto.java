package hu.onlab.easylearner.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LessonWithNamesDto {
        Integer Id;
        String TeacherName;
        String StudentName;
        String Info;
        Float StartTime;
        Float EndTime;
        String Longitude;
        String Latitude;
        Integer Payment;
        String LevelName;
        String TopicName;
}
