package dev.pierluigibuttazzo.backendpreparazione.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Objects;

public class CorsoDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "ID del corso, generato automaticamente dal sistema", example = "1")
    private Long corsoId;

    @Schema(description = "Titolo del corso", example = "Corso di Programmazione Java")
    private String titolo;

    @Schema(description = "Data e ora di inizio del corso", example = "2023-10-20 09:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataOraInizio;

    @Schema(description = "Luogo dove si svolge il corso", example = "Roma")
    private String luogo;

    @Schema(description = "Numero di posti disponibili per il corso", example = "30")
    private Integer disponibilita;

    public CorsoDTO() {
    }

    public CorsoDTO(Long corsoId, String titolo, LocalDateTime dataOraInizio, String luogo, Integer disponibilita) {
        this.corsoId = corsoId;
        this.titolo = titolo;
        this.dataOraInizio = dataOraInizio;
        this.luogo = luogo;
        this.disponibilita = disponibilita;
    }

    public Long getCorsoId() {
        return corsoId;
    }

    public void setCorsoId(Long corsoId) {
        this.corsoId = corsoId;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDateTime getDataOraInizio() {
        return dataOraInizio;
    }

    public void setDataOraInizio(LocalDateTime dataOraInizio) {
        this.dataOraInizio = dataOraInizio;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public Integer getDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita(Integer disponibilita) {
        this.disponibilita = disponibilita;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CorsoDTO corsoDTO = (CorsoDTO) o;
        return Objects.equals(corsoId, corsoDTO.corsoId) && Objects.equals(titolo, corsoDTO.titolo) && Objects.equals(dataOraInizio, corsoDTO.dataOraInizio) && Objects.equals(luogo, corsoDTO.luogo) && Objects.equals(disponibilita, corsoDTO.disponibilita);
    }

    @Override
    public int hashCode() {
        return Objects.hash(corsoId, titolo, dataOraInizio, luogo, disponibilita);
    }

    @Override
    public String toString() {
        return "CorsoDTO{" +
                "corsoId=" + corsoId +
                ", titolo='" + titolo + '\'' +
                ", dataOraInizio=" + dataOraInizio +
                ", luogo='" + luogo + '\'' +
                ", disponibilita=" + disponibilita +
                '}';
    }
}
