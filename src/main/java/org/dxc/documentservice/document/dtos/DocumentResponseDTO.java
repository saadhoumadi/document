package org.dxc.documentservice.document.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentResponseDTO {
    private Long id;
    private String title;
    private String hasAccess;
    private String emitterEmail;
    private String downloadURL;
    private Date insertDate;
}
