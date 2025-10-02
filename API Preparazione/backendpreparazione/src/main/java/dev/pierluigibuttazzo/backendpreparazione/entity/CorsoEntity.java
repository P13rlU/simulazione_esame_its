package dev.pierluigibuttazzo.backendpreparazione.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "corsi")
public class CorsoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "corso_id")
    private Long corsoId;

    @Column(name = "titolo", nullable = false, length = 50)
    private  String titolo;

    @Column(name = "data_ora_inizio", nullable = false)
    private LocalDateTime dataOraInizio;

    @Column(name= "luogo", nullable = false, length = 100)
    private String luogo;

    @Column(name= "disponibilita", nullable = false)
    private Integer disponibilita;

    @OneToMany(mappedBy = "corso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<IscrizioneEntity> iscrizioni = new ArrayList<>();

    public CorsoEntity() {
    }

    public CorsoEntity(Long corsoId, String titolo, LocalDateTime dataOraInizio, String luogo, Integer disponibilita, List<IscrizioneEntity> iscrizioni) {
        this.corsoId = corsoId;
        this.titolo = titolo;
        this.dataOraInizio = dataOraInizio;
        this.luogo = luogo;
        this.disponibilita = disponibilita;
        this.iscrizioni = iscrizioni;
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

    public List<IscrizioneEntity> getIscrizioni() {
        return iscrizioni;
    }

    public void setIscrizioni(List<IscrizioneEntity> iscrizioni) {
        this.iscrizioni = iscrizioni;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CorsoEntity that = (CorsoEntity) o;
        return Objects.equals(corsoId, that.corsoId) && Objects.equals(titolo, that.titolo) && Objects.equals(dataOraInizio, that.dataOraInizio) && Objects.equals(luogo, that.luogo) && Objects.equals(disponibilita, that.disponibilita) && Objects.equals(iscrizioni, that.iscrizioni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(corsoId, titolo, dataOraInizio, luogo, disponibilita, iscrizioni);
    }

    @Override
    public String toString() {
        return "CorsoEntity{" +
                "corsoId=" + corsoId +
                ", titolo='" + titolo + '\'' +
                ", dataOraInizio=" + dataOraInizio +
                ", luogo='" + luogo + '\'' +
                ", disponibilita=" + disponibilita +
                ", iscrizioni=" + iscrizioni +
                '}';
    }
}
