package org.dxc.documentservice.document.dtos;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DocumentRequestDTO {

    private String title;

    private Date insertDate;

    private Long teamLeadId;

    private List<Long> teamsId;

}
