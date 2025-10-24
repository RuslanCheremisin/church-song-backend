package rus.cheremisin.churchsong.mapper.songparts;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import rus.cheremisin.churchsong.DTO.songparts.AudioPartDTO;
import rus.cheremisin.churchsong.entity.songparts.AudioPart;

@Mapper(componentModel = "spring")
public interface AudioPartMapper {

    AudioPartDTO toDto(AudioPart song);

    @Mapping(target = "id", ignore = true)
    AudioPart toEntity(AudioPartDTO dto);
}
