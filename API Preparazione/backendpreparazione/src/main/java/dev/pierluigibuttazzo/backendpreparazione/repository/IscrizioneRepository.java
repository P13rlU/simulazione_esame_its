package dev.pierluigibuttazzo.backendpreparazione.repository;

import dev.pierluigibuttazzo.backendpreparazione.entity.IscrizioneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IscrizioneRepository extends JpaRepository<IscrizioneEntity, Long> {

    @Query("SELECT i FROM IscrizioneEntity i JOIN FETCH i.corso WHERE " +
            "(:corsoId IS NULL OR i.corso.corsoId = :corsoId) AND " +
            "(:luogo IS NULL OR UPPER(i.corso.luogo) LIKE UPPER(CONCAT('%', CAST(:luogo AS text), '%')))")
    List<IscrizioneEntity> findWithFilters(
            @Param("corsoId") Long corsoId,
            @Param("luogo") String luogo
    );
}
