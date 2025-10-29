package rus.cheremisin.churchsong.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import rus.cheremisin.churchsong.DTO.FullSongDTO;
import rus.cheremisin.churchsong.DTO.PatchSongDTO;
import rus.cheremisin.churchsong.DTO.SimpleSongDTO;
import rus.cheremisin.churchsong.entity.Song;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface SongMapper {

    FullSongDTO toFullSongDto(Song song);
    SimpleSongDTO toSimpleSongDto(Song song);

    @Mapping(target = "id", ignore = true)
    Song toEntity(FullSongDTO dto);

    List<FullSongDTO> toDtoList(List<Song> songs);

    @Mapping(target = "origRec", ignore = true)
    @Mapping(target = "songParts", ignore = true)
    Song mergeToEntity(PatchSongDTO dto, @MappingTarget Song song);

}
