package dev.pierluigibuttazzo.backendpreparazione.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

public class IscrizioneDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "ID dell'iscrizione, generato automaticamente dal sistema", example = "1")
    private Long iscrizioneId;

    @Schema(description = "Corso a cui il partecipante si è iscritto", example = "1")
    private CorsoDTO corso;

    @Schema(description = "Nome del partecipante", example = "Mario")
    private String partecipanteNome;

    @Schema(description = "Cognome del partecipante", example = "Rossi")
    private String partecipanteCognome;

    @Schema(description = "Email del partecipante", example = "mario.rossi@example.com")
    private String partecipanteEmail;

    @Schema(description = "Data e ora dell'iscrizione", example = "2023-10-15 14:30:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String dataOraIscrizione;

    public IscrizioneDTO() {
    }

    public IscrizioneDTO(Long iscrizioneId, CorsoDTO corso, String partecipanteNome, String partecipanteCognome, String partecipanteEmail, String dataOraIscrizione) {
        this.iscrizioneId = iscrizioneId;
        this.corso = corso;
        this.partecipanteNome = partecipanteNome;
        this.partecipanteCognome = partecipanteCognome;
        this.partecipanteEmail = partecipanteEmail;
        this.dataOraIscrizione = dataOraIscrizione;
    }

    public Long getIscrizioneId() {
        return iscrizioneId;
    }

    public void setIscrizioneId(Long iscrizioneId) {
        this.iscrizioneId = iscrizioneId;
    }

    public CorsoDTO getCorso() {
        return corso;
    }

    public void setCorso(CorsoDTO corso) {
        this.corso = corso;
    }

    public String getPartecipanteNome() {
        return partecipanteNome;
    }

    public void setPartecipanteNome(String partecipanteNome) {
        this.partecipanteNome = partecipanteNome;
    }

    public String getPartecipanteCognome() {
        return partecipanteCognome;
    }

    public void setPartecipanteCognome(String partecipanteCognome) {
        this.partecipanteCognome = partecipanteCognome;
    }

    public String getPartecipanteEmail() {
        return partecipanteEmail;
    }

    public void setPartecipanteEmail(String partecipanteEmail) {
        this.partecipanteEmail = partecipanteEmail;
    }

    public String getDataOraIscrizione() {
        return dataOraIscrizione;
    }

    public void setDataOraIscrizione(String dataOraIscrizione) {
        this.dataOraIscrizione = dataOraIscrizione;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        IscrizioneDTO that = (IscrizioneDTO) o;
        return Objects.equals(iscrizioneId, that.iscrizioneId) && Objects.equals(corso, that.corso) && Objects.equals(partecipanteNome, that.partecipanteNome) && Objects.equals(partecipanteCognome, that.partecipanteCognome) && Objects.equals(partecipanteEmail, that.partecipanteEmail) && Objects.equals(dataOraIscrizione, that.dataOraIscrizione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iscrizioneId, corso, partecipanteNome, partecipanteCognome, partecipanteEmail, dataOraIscrizione);
    }

    @Override
    public String toString() {
        return "IscrizioneDTO{" + "iscrizioneId=" + iscrizioneId + ", corso=" + corso + ", partecipanteNome='" + partecipanteNome + '\'' + ", partecipanteCognome='" + partecipanteCognome + '\'' + ", partecipanteEmail='" + partecipanteEmail + '\'' + ", dataOraIscrizione='" + dataOraIscrizione + '\'' + '}';
    }
}
