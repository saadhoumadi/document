package org.dxc.documentservice.document;

import org.dxc.documentservice.document.dtos.DocumentResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DocumentMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "emitterEmail", target = "emitterEmail")
    @Mapping(source = "hasAccess", target = "hasAccess")
    @Mapping(target = "insertDate", source = "insertDate")
    @Mapping(target = "downloadURL", expression = "java(\"http://localhost:8082/documents/download/\" + document.getId())")
    DocumentResponseDTO toDTO(Document document);
    Document toEntity(DocumentResponseDTO dto);
}
