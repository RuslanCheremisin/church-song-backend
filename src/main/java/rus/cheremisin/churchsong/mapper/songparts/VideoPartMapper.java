package rus.cheremisin.churchsong.mapper.songparts;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import rus.cheremisin.churchsong.DTO.songparts.VideoPartDTO;
import rus.cheremisin.churchsong.entity.songparts.VideoPart;

@Mapper(componentModel = "spring")
public interface VideoPartMapper {

    VideoPartDTO toDto(VideoPart song);

    @Mapping(target = "id", ignore = true)
    VideoPart toEntity(VideoPartDTO dto);
}
