package org.dxc.documentservice.document;

import org.dxc.documentservice.document.dtos.DocumentResponseDTO;
import org.dxc.documentservice.team.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/documents")
@RestController
@CrossOrigin("*")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping("/upload")
    public DocumentResponseDTO uploadFile(@RequestParam("file") MultipartFile file,
                                          @RequestParam("title") String title,
                                          @RequestParam("hasAccess") String hasAccess,
                                          @RequestParam("EmitterEmail") String EmitterEmail,
                                          @RequestParam("teamIds") List<Long> teamIds) throws Exception {

        return documentService.saveDocument(file,title,hasAccess,EmitterEmail,teamIds);

    }

    @PutMapping("/update/{documentId}")
    public Document updateDocument(@PathVariable Long documentId,
                                   @RequestParam("file") MultipartFile file,
                                   @RequestParam("title") String title,
                                   @RequestParam("hasAccess") String hasAccess,
                                   @RequestParam("teamIds") List<Long> teamIds) throws IOException {

        return documentService.updateDocument(documentId,file,title,hasAccess,teamIds);
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) throws Exception {
        Document document = null;
        document = documentService.getDocument(fileId);
        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(document.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\""
                                + "\"")
                .body(new ByteArrayResource(document.getContent()));
    }

    @GetMapping("/all")
    public List<DocumentResponseDTO> getAllDocuments(){
        return documentService.getAllDocuments();
    }

    @GetMapping("/teams")
    public List<Team> getAllTeams(){
        return documentService.getAllTeams();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDocument(@PathVariable Long id){
        documentService.deleteDocument(id);
    }
}
