package dev.pierluigibuttazzo.backendpreparazione.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "iscrizioni")
public class IscrizioneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ← FONDAMENTALE!
    @Column(name = "iscrizione_id")
    private Long iscrizioneId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "corso_id", nullable = false)
    private CorsoEntity corso;

    @Column(name = "partecipante_nome", nullable = false, length = 30)
    private String partecipanteNome;

    @Column(name = "partecipante_cognome", nullable = false, length = 30)
    private String partecipanteCognome;

    @Column(name = "partecipante_email", nullable = false, length = 50)
    private String partecipanteEmail;

    @Column(name = "data_ora_iscrizione", nullable = false)
    private LocalDateTime dataOraIscrizione;

    public IscrizioneEntity() {
    }

    public IscrizioneEntity(Long iscrizioneId, CorsoEntity corso, String partecipanteNome, String partecipanteCognome, String partecipanteEmail, LocalDateTime dataOraIscrizione) {
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

    public CorsoEntity getCorso() {
        return corso;
    }

    public void setCorso(CorsoEntity corso) {
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

    public LocalDateTime getDataOraIscrizione() {
        return dataOraIscrizione;
    }

    public void setDataOraIscrizione(LocalDateTime dataOraIscrizione) {
        this.dataOraIscrizione = dataOraIscrizione;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        IscrizioneEntity that = (IscrizioneEntity) o;
        return Objects.equals(iscrizioneId, that.iscrizioneId) && Objects.equals(corso, that.corso) && Objects.equals(partecipanteNome, that.partecipanteNome) && Objects.equals(partecipanteCognome, that.partecipanteCognome) && Objects.equals(partecipanteEmail, that.partecipanteEmail) && Objects.equals(dataOraIscrizione, that.dataOraIscrizione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iscrizioneId, corso, partecipanteNome, partecipanteCognome, partecipanteEmail, dataOraIscrizione);
    }

    @Override
    public String toString() {
        return "IscrizioneEntity{" +
                "iscrizioneId=" + iscrizioneId +
                ", corso=" + corso +
                ", partecipanteNome='" + partecipanteNome + '\'' +
                ", partecipanteCognome='" + partecipanteCognome + '\'' +
                ", partecipanteEmail='" + partecipanteEmail + '\'' +
                ", dataOraIscrizione=" + dataOraIscrizione +
                '}';
    }
}
