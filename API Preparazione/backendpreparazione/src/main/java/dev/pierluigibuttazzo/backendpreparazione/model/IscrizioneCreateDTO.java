package dev.pierluigibuttazzo.backendpreparazione.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

public class IscrizioneCreateDTO {
    @Schema(description = "ID del corso a cui iscriversi", example = "1")
    private Long corsoId;

    @Schema(description = "Nome del partecipante", example = "Mario")
    private String partecipanteNome;

    @Schema(description = "Cognome del partecipante", example = "Rossi")
    private String partecipanteCognome;

    @Schema(description = "Email del partecipante", example = "mario.rossi@example.com")
    private String partecipanteEmail;

    public IscrizioneCreateDTO() {
    }

    public IscrizioneCreateDTO(Long corsoId, String partecipanteNome, String partecipanteCognome, String partecipanteEmail) {
        this.corsoId = corsoId;
        this.partecipanteNome = partecipanteNome;
        this.partecipanteCognome = partecipanteCognome;
        this.partecipanteEmail = partecipanteEmail;
    }

    public Long getCorsoId() {
        return corsoId;
    }

    public void setCorsoId(Long corsoId) {
        this.corsoId = corsoId;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        IscrizioneCreateDTO that = (IscrizioneCreateDTO) o;
        return Objects.equals(corsoId, that.corsoId) && Objects.equals(partecipanteNome, that.partecipanteNome) && Objects.equals(partecipanteCognome, that.partecipanteCognome) && Objects.equals(partecipanteEmail, that.partecipanteEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(corsoId, partecipanteNome, partecipanteCognome, partecipanteEmail);
    }

    @Override
    public String toString() {
        return "IscrizioneCreateDTO{" +
                "corsoId=" + corsoId +
                ", partecipanteNome='" + partecipanteNome + '\'' +
                ", partecipanteCognome='" + partecipanteCognome + '\'' +
                ", partecipanteEmail='" + partecipanteEmail + '\'' +
                '}';
    }
}
