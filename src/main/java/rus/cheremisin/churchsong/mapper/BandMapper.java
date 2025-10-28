package rus.cheremisin.churchsong.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import rus.cheremisin.churchsong.DTO.BandDTO;
import rus.cheremisin.churchsong.entity.Band;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface BandMapper {

    BandDTO toDto(Band song);

    @Mapping(target = "id", ignore = true)
    Band toEntity(BandDTO dto);
}
