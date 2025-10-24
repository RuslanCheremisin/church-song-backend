package rus.cheremisin.churchsong.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import rus.cheremisin.churchsong.DTO.BandDTO;
import rus.cheremisin.churchsong.entity.Band;

@Mapper(componentModel = "spring")
public interface BandMapper {

    BandDTO toDto(Band song);

    @Mapping(target = "id", ignore = true)
    Band toEntity(BandDTO dto);
}
