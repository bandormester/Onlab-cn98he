package hu.onlab.easylearner.model.mapper;

import hu.onlab.easylearner.model.dto.TopicDto;
import hu.onlab.easylearner.model.entities.Topic;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TopicMapper {
    public TopicDto entityToDto(Topic item);
}
