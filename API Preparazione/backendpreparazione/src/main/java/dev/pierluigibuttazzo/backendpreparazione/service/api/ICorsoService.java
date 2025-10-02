package dev.pierluigibuttazzo.backendpreparazione.service.api;

import dev.pierluigibuttazzo.backendpreparazione.entity.CorsoEntity;
import dev.pierluigibuttazzo.backendpreparazione.model.CorsoDTO;

import java.util.List;

public interface ICorsoService {

    List<CorsoDTO> findCorsi(String param);
}
