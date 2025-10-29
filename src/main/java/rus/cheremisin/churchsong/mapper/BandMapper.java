package rus.cheremisin.churchsong.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import rus.cheremisin.churchsong.DTO.BandDTO;
import rus.cheremisin.churchsong.DTO.PatchBandDTO;
import rus.cheremisin.churchsong.entity.Band;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BandMapper {

    BandDTO toDto(Band song);

    @Mapping(target = "id", ignore = true)
    Band toEntity(BandDTO dto);

    @Mapping(target = "id", ignore = true)
    List<BandDTO> toDtoList(List<Band> all);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "leader", ignore = true)
    @Mapping(target = "bandAvatar", ignore = true)
    @Mapping(target = "members", ignore = true)
    @Mapping(target = "songs", ignore = true)
    Band mergeToEntity(PatchBandDTO dto, @MappingTarget Band band);
}
