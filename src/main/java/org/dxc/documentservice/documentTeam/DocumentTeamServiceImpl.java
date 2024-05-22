package org.dxc.documentservice.documentTeam;

import jakarta.transaction.Transactional;
import org.dxc.documentservice.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentTeamServiceImpl implements DocumentTeamService {

    @Autowired
    private DocumentTeamRepository documentTeamRepository;

    @Override
    public DocumentTeam save(DocumentTeam documentTeam) {
        return documentTeamRepository.save(documentTeam);
    }

     @Override
     public DocumentTeam updateByDocumentId(DocumentTeam documentTeam, Long documentId){
        DocumentTeam updatedDocumentTeam= documentTeamRepository.findByDocumentId(documentId);
        updatedDocumentTeam.setTeamId(documentTeam.getTeamId());
        return updatedDocumentTeam;
     }

    @Override
    public List<DocumentTeam> getDocumentsById(Long id) {
        return documentTeamRepository.findAllById(id);
    }

    @Override
    public Document deleteByDocumentId(Long id) {
        documentTeamRepository.deleteByDocumentId(id);
        return null;
    }

    @Override
    public List<String> findAllByDocumentId(Long documentId) {
        return documentTeamRepository.findAllByDocumentId(documentId);
    }

}
