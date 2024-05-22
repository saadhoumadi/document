package org.dxc.documentservice.documentTeam;

import org.dxc.documentservice.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/document-team")
@CrossOrigin("*")
public class DocumentTeamController {

    @Autowired
    private DocumentTeamService documentTeamService;

    @PostMapping
    DocumentTeam save(DocumentTeam documentTeam){
        return documentTeamService.save(documentTeam);
    }

    @GetMapping
    List<DocumentTeam> getDocumentsById(Long id){
        return documentTeamService.getDocumentsById(id);
    }

    @GetMapping("/allByDocument/{documentId}")
    List<String> findAllByDocument(@PathVariable Long documentId){
        return documentTeamService.findAllByDocumentId(documentId);
    }
}
