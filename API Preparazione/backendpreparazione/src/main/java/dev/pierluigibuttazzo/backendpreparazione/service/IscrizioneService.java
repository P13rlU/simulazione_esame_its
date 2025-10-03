package dev.pierluigibuttazzo.backendpreparazione.service;

import dev.pierluigibuttazzo.backendpreparazione.entity.CorsoEntity;
import dev.pierluigibuttazzo.backendpreparazione.entity.IscrizioneEntity;
import dev.pierluigibuttazzo.backendpreparazione.exception.ConflictException;
import dev.pierluigibuttazzo.backendpreparazione.exception.ResourceNotFoundException;
import dev.pierluigibuttazzo.backendpreparazione.model.IscrizioneCreateDTO;
import dev.pierluigibuttazzo.backendpreparazione.model.IscrizioneDTO;
import dev.pierluigibuttazzo.backendpreparazione.repository.CorsoRepository;
import dev.pierluigibuttazzo.backendpreparazione.repository.IscrizioneRepository;
import dev.pierluigibuttazzo.backendpreparazione.service.api.IIscrizioneService;
import dev.pierluigibuttazzo.backendpreparazione.utility.mapper.IscrizioneMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IscrizioneService implements IIscrizioneService {

    @Autowired
    private IscrizioneRepository iscrizioneRepository;

    @Autowired
    private IscrizioneMapper iscrizioneMapper;

    @Autowired
    private CorsoRepository corsoRepository;

    @Override
    public List<IscrizioneDTO> findIscrizioni(Long corsoId, String luogo) {
        // Pulisci il parametro luogo (rimuovi spazi)
        String luogoPulito = (luogo != null) ? luogo.trim() : null;
        if (luogoPulito != null && luogoPulito.isEmpty()) {
            luogoPulito = null;
        }

        // Una sola query gestisce tutti i casi:
        // - corsoId=null, luogo=null → tutte le iscrizioni
        // - corsoId=123, luogo=null → solo per corso
        // - corsoId=null, luogo="Roma" → solo per luogo
        // - corsoId=123, luogo="Roma" → entrambi
        List<IscrizioneEntity> entities = iscrizioneRepository.findWithFilters(corsoId, luogoPulito);
        return iscrizioneMapper.iscrizioneEntityToDtoList(entities);
    }

    @Override
    @Transactional
    public IscrizioneDTO createIscrizione(IscrizioneCreateDTO createDTO) {
        // 2. Carica il corso
        CorsoEntity corso = corsoRepository.findById(createDTO.getCorsoId())
                .orElseThrow(() -> new ResourceNotFoundException("Corso non trovato con ID: " + createDTO.getCorsoId()));

        // 3. Controlla disponibilità posti
        if (corso.getDisponibilita() <= 0) {
            throw new ConflictException("Non ci sono posti disponibili per il corso: " + corso.getCorsoId()); //lancia eccezione
        }

        // 4. Crea l'iscrizione
        IscrizioneEntity iscrizione = new IscrizioneEntity();
        iscrizione.setCorso(corso);
        iscrizione.setPartecipanteNome(createDTO.getPartecipanteNome());
        iscrizione.setPartecipanteCognome(createDTO.getPartecipanteCognome());
        iscrizione.setPartecipanteEmail(createDTO.getPartecipanteEmail());
        iscrizione.setDataOraIscrizione(LocalDateTime.now());

        // 5. Salva l'iscrizione
        iscrizione = iscrizioneRepository.save(iscrizione);

        // 6. Riduci la disponibilità del corso
        corso.setDisponibilita(corso.getDisponibilita() - 1);
        corsoRepository.save(corso);

        // 7. Restituisci il DTO → MapStruct gestirà la conversione automaticamente
        return iscrizioneMapper.iscrizioneEntityToDto(iscrizione);
    }
}
