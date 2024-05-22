package org.dxc.documentservice.documentVersion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DocumentVersionRepository extends JpaRepository<DocumentVersion, Long> {

    @Query("SELECT dv.version FROM DocumentVersion dv WHERE dv.document.id = :documentId ORDER BY dv.version DESC LIMIT 1 ")
    Long getLastVersion( @Param("documentId") Long documentId);
}
