package hu.onlab.easylearner.model.mapper;

import hu.onlab.easylearner.model.dto.LevelDto;
import hu.onlab.easylearner.model.dto.TopicDto;
import hu.onlab.easylearner.model.entities.Level;
import hu.onlab.easylearner.model.entities.Topic;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LevelMapper {
    public LevelDto entityToDto(Level item);
}
