package dev.pierluigibuttazzo.backendpreparazione.utility.mapper;

import dev.pierluigibuttazzo.backendpreparazione.entity.CorsoEntity;
import dev.pierluigibuttazzo.backendpreparazione.model.CorsoDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CorsoMapper {

    CorsoDTO corsoEntityToDto(CorsoEntity corsoEntity);

    List<CorsoDTO> corsoEntityToDtoList(List<CorsoEntity> corsoEntities);
}
