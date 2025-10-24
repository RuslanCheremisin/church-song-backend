package rus.cheremisin.churchsong.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import rus.cheremisin.churchsong.DTO.OriginalRecordingDTO;
import rus.cheremisin.churchsong.entity.OriginalRecording;

@Mapper(componentModel = "spring")
public interface OriginalRecordMapper {

    OriginalRecordingDTO toDto(OriginalRecording song);

    @Mapping(target = "id", ignore = true)
    OriginalRecording toEntity(OriginalRecordingDTO dto);
}
