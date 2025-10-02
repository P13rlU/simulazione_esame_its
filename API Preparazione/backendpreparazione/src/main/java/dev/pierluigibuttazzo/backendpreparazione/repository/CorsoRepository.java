package dev.pierluigibuttazzo.backendpreparazione.repository;

import dev.pierluigibuttazzo.backendpreparazione.entity.CorsoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorsoRepository extends JpaRepository<CorsoEntity, Long> {
    List<CorsoEntity> findByTitoloContainingIgnoreCase(String titolo);

}
