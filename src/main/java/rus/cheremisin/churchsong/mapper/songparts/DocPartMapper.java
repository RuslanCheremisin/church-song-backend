package rus.cheremisin.churchsong.mapper.songparts;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import rus.cheremisin.churchsong.DTO.songparts.DocPartDTO;
import rus.cheremisin.churchsong.entity.songparts.DocPart;

@Mapper(componentModel = "spring")
public interface DocPartMapper {

    DocPartDTO toDto(DocPart song);

    @Mapping(target = "id", ignore = true)
    DocPart toEntity(DocPartDTO dto);
}
