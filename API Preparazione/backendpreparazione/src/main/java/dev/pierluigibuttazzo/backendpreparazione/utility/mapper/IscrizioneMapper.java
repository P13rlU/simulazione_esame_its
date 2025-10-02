package dev.pierluigibuttazzo.backendpreparazione.utility.mapper;

import dev.pierluigibuttazzo.backendpreparazione.entity.CorsoEntity;
import dev.pierluigibuttazzo.backendpreparazione.entity.IscrizioneEntity;
import dev.pierluigibuttazzo.backendpreparazione.model.CorsoDTO;
import dev.pierluigibuttazzo.backendpreparazione.model.IscrizioneCreateDTO;
import dev.pierluigibuttazzo.backendpreparazione.model.IscrizioneDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IscrizioneMapper {

    // Conversione principale
    IscrizioneDTO iscrizioneEntityToDto(IscrizioneEntity iscrizioneEntity);

    // Conversione per la lista
    List<IscrizioneDTO> iscrizioneEntityToDtoList(List<IscrizioneEntity> iscrizioneEntities);

    // Da CreateDTO a Entity (per il POST)
    IscrizioneEntity iscrizioneCreateDtoToEntity(IscrizioneCreateDTO createDTO);

    // NUOVO: CorsoEntity → CorsoDTO (MapStruct lo implementa automaticamente)
    CorsoDTO corsoEntityToDto(CorsoEntity corsoEntity);
}
