package org.dxc.documentservice.documentVersion;

import org.dxc.documentservice.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
public class DocumentVersionServiceImpl implements DocumentVersionService{

    @Autowired
    private DocumentVersionRepository documentVersionRepository;
    @Override
    public DocumentVersion saveDocumentVersion(MultipartFile file, String title,Document document, Long version) throws Exception {
        DocumentVersion documentVersion
                = new DocumentVersion(
                file.getBytes(),
                title,document,new Date(), version);
        return documentVersionRepository.save(documentVersion);
    }

    @Override
    public Long getDocumentLastVersion(Long documentId) {
        return documentVersionRepository.getLastVersion(documentId);
    }
}
