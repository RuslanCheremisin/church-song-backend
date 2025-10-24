package rus.cheremisin.churchsong.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import rus.cheremisin.churchsong.DTO.SongDTO;
import rus.cheremisin.churchsong.entity.Song;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SongMapper {

    SongDTO toDto(Song song);

    @Mapping(target = "id", ignore = true)
    Song toEntity(SongDTO dto);

    List<SongDTO> toDtoList(List<Song> songs);

    Song mergeToEntity(SongDTO dto, @MappingTarget Song song);

}
