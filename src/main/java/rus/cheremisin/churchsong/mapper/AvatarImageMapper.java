package rus.cheremisin.churchsong.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import rus.cheremisin.churchsong.DTO.AvatarImageDTO;
import rus.cheremisin.churchsong.entity.AvatarImage;

@Mapper(componentModel = "spring")
public interface AvatarImageMapper {

    AvatarImageDTO toDto(AvatarImage song);

    @Mapping(target = "id", ignore = true)
    AvatarImage toEntity(AvatarImageDTO dto);
}
