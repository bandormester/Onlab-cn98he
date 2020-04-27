package hu.onlab.easylearner.model.mapper;

import hu.onlab.easylearner.model.dto.LessonDto;
import hu.onlab.easylearner.model.entities.Lesson;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LessonMapper {
    public LessonDto entityToDto(Lesson item);
}
