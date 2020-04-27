package hu.onlab.easylearner.model.mapper

import hu.onlab.easylearner.model.dto.LearnersDto
import hu.onlab.easylearner.model.entities.LearnersKT
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface LearnersMapper {
    public fun entityToDto(item : LearnersKT) : LearnersDto
}