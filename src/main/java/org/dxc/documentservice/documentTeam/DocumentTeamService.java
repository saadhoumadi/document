package org.dxc.documentservice.documentTeam;

import org.dxc.documentservice.document.Document;

import java.util.List;
import java.util.Optional;

public interface DocumentTeamService {
    DocumentTeam save(DocumentTeam documentTeam);

    DocumentTeam updateByDocumentId(DocumentTeam documentTeam, Long id);
    List<DocumentTeam> getDocumentsById(Long id);
    Document deleteByDocumentId(Long id);

    List<String> findAllByDocumentId(Long documentId);
}
