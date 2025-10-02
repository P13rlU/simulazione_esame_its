package dev.pierluigibuttazzo.backendpreparazione.service.api;

import dev.pierluigibuttazzo.backendpreparazione.model.IscrizioneCreateDTO;
import dev.pierluigibuttazzo.backendpreparazione.model.IscrizioneDTO;

import java.util.List;

public interface IIscrizioneService {

    List<IscrizioneDTO> findIscrizioni(Long corsoId, String luogo);

    IscrizioneDTO createIscrizione(IscrizioneCreateDTO iscrizioneCreateDTO);
}
