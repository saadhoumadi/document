package org.dxc.documentservice.documentVersion;
import org.dxc.documentservice.document.Document;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentVersionService {
    DocumentVersion saveDocumentVersion(MultipartFile file, String title, Document document, Long version) throws Exception;

    Long getDocumentLastVersion(Long documentId);

}
