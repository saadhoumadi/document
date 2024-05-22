package org.dxc.documentservice.documentTeam;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dxc.documentservice.document.Document;

@Entity
@NoArgsConstructor
@Data
public class DocumentTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="document_id", nullable=false)
    private Document document;

    @Column(name = "team_id")
    private Long teamId;
}
