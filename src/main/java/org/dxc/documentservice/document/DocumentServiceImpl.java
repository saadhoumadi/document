package org.dxc.documentservice.document;

import jakarta.transaction.Transactional;
import org.dxc.documentservice.document.dtos.DocumentResponseDTO;
import org.dxc.documentservice.documentTeam.DocumentTeam;
import org.dxc.documentservice.documentTeam.DocumentTeamService;
import org.dxc.documentservice.documentVersion.DocumentVersion;
import org.dxc.documentservice.documentVersion.DocumentVersionService;
import org.dxc.documentservice.team.Team;
import org.dxc.documentservice.team.openFeign.TeamRestClient;
import org.dxc.documentservice.user.openFeign.UserRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DocumentServiceImpl implements DocumentService{

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentTeamService documentTeamService;

    @Autowired
    private DocumentVersionService documentVersionService;

    @Autowired
    private UserRestClient userRestClient;

    @Autowired
    private TeamRestClient teamRestClient;

    @Autowired
    private DocumentMapper documentMapper;
    @Override
    public DocumentResponseDTO saveDocument(MultipartFile file, String title, String hasAccess, String EmitterEmail, List<Long> teamsId) throws Exception {

        /*     Check if user exists
        User user;
        try {
            user= userRestClient.userById(id);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }*/

        Document attachment
                    = new Document(
                    file.getContentType(),
                    file.getBytes(),title,hasAccess,new Date(), EmitterEmail);
        documentRepository.save(attachment);

        documentVersionService.saveDocumentVersion(file,title,attachment,1L);

        if(attachment.getHasAccess().equals("private")){
            for(Long teamId: teamsId){
                DocumentTeam documentTeam= new DocumentTeam();
                documentTeam.setDocument(attachment);
                documentTeam.setTeamId(teamId);
                documentTeamService.save(documentTeam);
            }
        }

        return documentMapper.toDTO(attachment);
    }

    @Override
    public Document updateDocument(Long documentId, MultipartFile file, String title,String hasAccess, List<Long> teamsId) throws IOException {
        Document existingDocument= documentRepository.findById(documentId).orElse(null);
        existingDocument.setTitle(title);
        existingDocument.setInsertDate(new Date());
        existingDocument.setHasAccess(hasAccess);
        if (file != null) {
            existingDocument.setFileType(file.getContentType());
            existingDocument.setContent(file.getBytes());
        }
        Document updatedDocument = documentRepository.save(existingDocument);

        try {
            documentVersionService.saveDocumentVersion(file,title,updatedDocument,
                                                documentVersionService.getDocumentLastVersion(updatedDocument.getId())+1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if(existingDocument.getHasAccess().equals("private") || hasAccess.equals("private")){
            documentTeamService.deleteByDocumentId(documentId);
        }

        if(hasAccess.equals("private")){
            for(Long teamId: teamsId){
                DocumentTeam documentTeam= new DocumentTeam();
                documentTeam.setDocument(updatedDocument);
                documentTeam.setTeamId(teamId);
                documentTeamService.save(documentTeam);
            }
        }
        return updatedDocument;
    }

    @Override
    public Document getDocument(Long fileId) throws Exception {
        return documentRepository.findById(fileId) .orElseThrow(
                () -> new Exception("File not found with Id: " + fileId));
    }

    @Override
    public List<DocumentResponseDTO> getAllDocuments() {
        List<Document> documents= documentRepository.findAll();
        return documents.stream().map((doc)->documentMapper.toDTO(doc)).collect(Collectors.toList());
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRestClient.getAllTeams();
    }


    @Override
    public void deleteDocument(Long id){
        documentRepository.deleteById(id);
    }

}
