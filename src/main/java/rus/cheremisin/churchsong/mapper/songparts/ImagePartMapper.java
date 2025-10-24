package rus.cheremisin.churchsong.mapper.songparts;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import rus.cheremisin.churchsong.DTO.songparts.ImagePartDTO;
import rus.cheremisin.churchsong.entity.songparts.ImagePart;

@Mapper(componentModel = "spring")
public interface ImagePartMapper {

    ImagePartDTO toDto(ImagePart song);

    @Mapping(target = "id", ignore = true)
    ImagePart toEntity(ImagePartDTO dto);
}
