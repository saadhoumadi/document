package org.dxc.documentservice.document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dxc.documentservice.documentTeam.DocumentTeam;
import org.dxc.documentservice.documentVersion.DocumentVersion;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor @Data
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileType;

    private String title;

    private String hasAccess;

    private Date insertDate;

    @Lob
    @Column(length = 500000)
    private byte[] content;

    private String emitterEmail; //email  //theme

    @JsonIgnore
    @OneToMany(mappedBy="document")
    private List<DocumentTeam> teams;

    @JsonIgnore
    @OneToMany(mappedBy="document")
    private List<DocumentVersion> versions;


    public Document(String fileType, byte[] content, String title, String hasAccess, Date insertDate, String emitterEmail) {
        this.fileType = fileType;
        this.content = content;
        this.title=title;
        this.hasAccess=hasAccess;
        this.insertDate=insertDate;
        this.emitterEmail=emitterEmail;
    }

}
