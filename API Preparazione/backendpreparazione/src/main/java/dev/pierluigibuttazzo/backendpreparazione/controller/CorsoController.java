package dev.pierluigibuttazzo.backendpreparazione.controller;

import dev.pierluigibuttazzo.backendpreparazione.model.CorsoDTO;
import dev.pierluigibuttazzo.backendpreparazione.service.api.ICorsoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/courses")
@Tag(name = "Corsi", description = "Gestione dei corsi formativi")
public class CorsoController {

    @Autowired
    private ICorsoService corsoService;

    @Operation(
            summary = "Recupera la lista dei corsi",
            description = "Ritorna tutti i corsi disponibili. "
                    + "Se viene passato un parametro di ricerca, filtra i corsi corrispondenti."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista corsi trovata",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CorsoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Parametro non valido", content = @Content),
            @ApiResponse(responseCode = "500", description = "Errore interno del server", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<CorsoDTO>> findCorsi(
            @Parameter(description = "Parametro di ricerca opzionale per filtrare i corsi")
            @RequestParam(value = "titolo", required = false) String param) {

        List<CorsoDTO> corsi = corsoService.findCorsi(param);
        return new ResponseEntity<>(corsi, HttpStatus.OK);
    }
}
