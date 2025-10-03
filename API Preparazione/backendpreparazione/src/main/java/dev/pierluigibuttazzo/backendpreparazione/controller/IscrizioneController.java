package dev.pierluigibuttazzo.backendpreparazione.controller;

import dev.pierluigibuttazzo.backendpreparazione.model.IscrizioneCreateDTO;
import dev.pierluigibuttazzo.backendpreparazione.model.IscrizioneDTO;
import dev.pierluigibuttazzo.backendpreparazione.service.api.IIscrizioneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/enrollments")
@Tag(name = "Iscrizioni", description = "Gestione delle iscrizioni ai corsi")
public class IscrizioneController {

    @Autowired
    private IIscrizioneService iscrizioneService;

    @GetMapping
    @Operation(
            summary = "Recupera un elenco di tutte le iscrizioni effettuate",
            description = "Ritorna le iscrizioni filtrate per corsoId e/o luogo."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista iscrizioni trovata",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IscrizioneDTO.class))),
            @ApiResponse(responseCode = "400", description = "Parametro non valido", content = @Content),
            @ApiResponse(responseCode = "500", description = "Errore interno del server", content = @Content)
    })
    public ResponseEntity<List<IscrizioneDTO>> findIscrizioni(
            @Parameter(description = "ID del corso per filtrare le iscrizioni")
            @RequestParam(required = false) Long corsoId,

            @Parameter(description = "Luogo del corso per filtrare le iscrizioni")
            @RequestParam(required = false) String luogo) {

        List<IscrizioneDTO> iscrizioni = iscrizioneService.findIscrizioni(corsoId, luogo);
        return new ResponseEntity<>(iscrizioni, HttpStatus.OK);
    }

    @PostMapping
    @Operation(
            summary = "Crea una nuova iscrizione",
            description = "Crea un'iscrizione con i campi corso_id, partecipante_nome, partecipante_cognome, partecipante_email. " +
                    "La data e ora di iscrizione viene impostata automaticamente. " +
                    "I posti disponibili del corso vengono ridotti di 1."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Iscrizione creata con successo",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IscrizioneDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dati di input non validi", content = @Content),
            @ApiResponse(responseCode = "404", description = "Corso non trovato", content = @Content),
            @ApiResponse(responseCode = "409", description = "Nessun posto disponibile nel corso", content = @Content),
            @ApiResponse(responseCode = "500", description = "Errore interno del server", content = @Content)
    })
    public ResponseEntity<IscrizioneDTO> createIscrizione(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dati dell'iscrizione da creare",
                    required = true
            )
            @Valid @RequestBody IscrizioneCreateDTO createDTO) {

        IscrizioneDTO iscrizioneDTO = iscrizioneService.createIscrizione(createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(iscrizioneDTO);
    }
}
