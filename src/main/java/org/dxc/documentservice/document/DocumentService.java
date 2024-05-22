package org.dxc.documentservice.document;

import org.dxc.documentservice.document.dtos.DocumentResponseDTO;
import org.dxc.documentservice.team.Team;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DocumentService {
    DocumentResponseDTO saveDocument(MultipartFile file, String title, String hasAccess, String EmitterEmail, List<Long> teamsId) throws Exception;

    Document updateDocument(Long documentId, MultipartFile file, String title,String hasAccess, List<Long> teamsId) throws IOException;
    Document getDocument(Long fileId) throws Exception;

    List<DocumentResponseDTO> getAllDocuments();

    List<Team> getAllTeams();

    void deleteDocument(Long documentId);
}
