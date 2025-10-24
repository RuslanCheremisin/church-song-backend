package rus.cheremisin.churchsong.mapper.songparts;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import rus.cheremisin.churchsong.DTO.songparts.SongPartDTO;
import rus.cheremisin.churchsong.entity.songparts.SongPart;

@Mapper(componentModel = "spring")
public interface SongPartMapper {

    SongPartDTO toDto(SongPart song);

    @Mapping(target = "id", ignore = true)
    SongPart toEntity(SongPartDTO dto);
}
