package com.restbnote.rest.repositories;

import com.restbnote.rest.models.entities.ControleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ControleRepository extends JpaRepository<ControleEntity, String> {
    @Query("SELECT mailEtudiantsID, matiereID, SUM(note * coef) / SUM(coef) FROM ControleEntity GROUP BY mailEtudiantsID, matiereID")
    List<Object[]> findWeightedAverageNoteByStudentAndSubject();
    @Query("SELECT mailEtudiantsID, SUM(note * coef) / SUM(coef) FROM ControleEntity GROUP BY mailEtudiantsID")
    List<Object[]> findWeightedAverageNoteByStudent();


    @Query("SELECT AVG(note * coef) / AVG(coef) AS average " +
            "FROM ControleEntity " +
            "GROUP BY mailEtudiantsID " +
            "ORDER BY average DESC " +
            "LIMIT 1")
    Double findBestClassAverage();

    @Query("SELECT AVG(note * coef) / AVG(coef) AS average " +
            "FROM ControleEntity " +
            "GROUP BY mailEtudiantsID " +
            "ORDER BY average ASC " +
            "LIMIT 1")
    Double findWorstClassAverage();









    List<ControleEntity> findAllByMailEtudiantsID(String mailEtudiantsID);
}
