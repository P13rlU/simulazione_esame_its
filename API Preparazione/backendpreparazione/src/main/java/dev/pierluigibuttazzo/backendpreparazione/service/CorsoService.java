package dev.pierluigibuttazzo.backendpreparazione.service;

import dev.pierluigibuttazzo.backendpreparazione.model.CorsoDTO;
import dev.pierluigibuttazzo.backendpreparazione.repository.CorsoRepository;
import dev.pierluigibuttazzo.backendpreparazione.service.api.ICorsoService;
import dev.pierluigibuttazzo.backendpreparazione.utility.mapper.CorsoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorsoService implements ICorsoService {

    @Autowired
    private CorsoRepository corsoRepository;

    @Autowired
    private CorsoMapper corsoMapper;

    @Override
    public List<CorsoDTO> findCorsi(String param) {
        if (param == null || param.isEmpty()) {
            return corsoMapper.corsoEntityToDtoList(corsoRepository.findAll());
        } else {
            return corsoMapper.corsoEntityToDtoList(corsoRepository.findByTitoloContainingIgnoreCase(param));
        }
    }
}
