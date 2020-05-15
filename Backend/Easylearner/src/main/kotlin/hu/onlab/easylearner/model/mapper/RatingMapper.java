package hu.onlab.easylearner.model.mapper;

import hu.onlab.easylearner.model.dto.RatingDto;
import hu.onlab.easylearner.model.entities.Rating;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RatingMapper {
    public RatingDto entityToDto(Rating item);
}
