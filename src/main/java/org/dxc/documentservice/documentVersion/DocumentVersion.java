package org.dxc.documentservice.documentVersion;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dxc.documentservice.document.Document;

import java.util.Date;

@Entity @NoArgsConstructor @AllArgsConstructor
@Data
public class DocumentVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Date insertDate;

    @Lob
    @Column(length = 500000)
    private byte[] content;

    private Long version;

    @ManyToOne
    @JoinColumn(name="document_id", nullable=false)
    private Document document;

    public DocumentVersion( byte[] content, String title,Document document, Date insertDate, Long version) {
        this.content = content;
        this.title=title;
        this.document=document;
        this.insertDate=insertDate;
        this.version=version;
    }
}
