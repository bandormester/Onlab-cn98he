package hu.onlab.easylearner.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LessonWithNamesDto {
        Integer Id;
        Integer TeacherId;
        Integer StudentId;
        String TeacherName;
        String StudentName;
        String Info;
        Long StartTime;
        Long EndTime;
        String Longitude;
        String Latitude;
        Integer Payment;
        String LevelName;
        String TopicName;
}
