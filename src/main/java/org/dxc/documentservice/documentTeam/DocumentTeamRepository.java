package org.dxc.documentservice.documentTeam;

import org.dxc.documentservice.document.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DocumentTeamRepository extends JpaRepository<DocumentTeam,Long> {
    List<DocumentTeam> findAllById(Long id);

    void deleteByDocumentId(Long documentId);

    DocumentTeam findByDocumentId(Long documentId);

    @Query("SELECT dt.teamId FROM DocumentTeam dt WHERE dt.document.id = :documentId")
    List<String> findAllByDocumentId(Long documentId);

}
